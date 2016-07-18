package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RecAddressBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IAddressChangeListener;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.IPopWindowProtocol;

/**
 * 包装类
 * Created by ${Duke} on 2016/7/14.
 */
public class ProvinceSortPopWindow implements IPopWindowProtocol, IModelChangeListener {
    Context mContext;
    PopupWindow mPopWindow;
    private TextView submit_tv;
    private ListView province_lv;
    private ListView city_lv;
    private ArrayAdapter<String> mProvinceAdapter;
    private ListView dist_lv;
    private ShopCarController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.GET_PROVINCE_ACTION_RESULT:
                    handleProvince((List<RecAddressBean>) msg.obj);
                    break;
                case IDiyMessage.GET_CITY_ACTION_RESULT:
                    handleCity((List<RecAddressBean>) msg.obj);
                    break;
                case IDiyMessage.GET_AREA_ACTION_RESULT:
                    handleArea((List<RecAddressBean>) msg.obj);
                    break;
            }
        }
    };
    private ArrayAdapter<String> mAreaAdapter;
    private List<RecAddressBean> mAreaAddressBeen;
    private IAddressChangeListener mListener;
    private RecAddressBean mProvince;
    private RecAddressBean mCity;
    private RecAddressBean mArea;
    private View leftv;

    private void handleArea(List<RecAddressBean> bean) {
        mAreaAddressBeen = bean;
        ArrayList<String> areaDatas = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            areaDatas.add(bean.get(i).getName());
        }
        mAreaAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1
                , android.R.id.text1, areaDatas);
        dist_lv.setAdapter(mAreaAdapter);

    }

    private List<RecAddressBean> mCityAddressBeen;
    private ArrayAdapter<String> mCityAdapter;

    private void handleCity(List<RecAddressBean> bean) {
        mCityAddressBeen = bean;
        ArrayList<String> cityDatas = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            cityDatas.add(bean.get(i).getName());
        }
        mCityAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1
                , android.R.id.text1, cityDatas);
        city_lv.setAdapter(mCityAdapter);
    }

    private List<RecAddressBean> mProvinceAddressBeen;

    private void handleProvince(List<RecAddressBean> bean) {
        mProvinceAddressBeen = bean;
        ArrayList<String> provinceDatas = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            provinceDatas.add(bean.get(i).getName());
        }
        mProvinceAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1
                , android.R.id.text1, provinceDatas);
        province_lv.setAdapter(mProvinceAdapter);
    }


    public ProvinceSortPopWindow(Context context) {
        mContext = context;
        initController();
        initView();
    }

    private void initController() {
        mController = new ShopCarController(mContext);
        mController.setListener(this);
    }

    @Override
    public void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.address_pop_view, null);

        leftv =  view.findViewById(R.id.left_v);
        leftv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDismiss();
            }
        });
        submit_tv = (TextView) view.findViewById(R.id.submit_tv);
        submit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {
                    if(mProvince!=null&&mCity!=null&&mArea!=null) {
                        mListener.onChange(mProvince, mCity, mArea);
                    }
                }
               onDismiss();
            }
        });
        province_lv = (ListView) view.findViewById(R.id.province_lv);
        city_lv = (ListView) view.findViewById(R.id.city_lv);
        dist_lv = (ListView) view.findViewById(R.id.dist_lv);
        mController.sendAsyncMessage(IDiyMessage.GET_PROVINCE_ACTION, 0);
        province_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCity=null;
                mArea=null;
                mProvince = mProvinceAddressBeen.get(position);
                String provinceCode = mProvince.getCode();
                mController.sendAsyncMessage(IDiyMessage.GET_CITY_ACTION, provinceCode);
                mAreaAddressBeen = new ArrayList<RecAddressBean>();
                handleArea(mAreaAddressBeen);
                //                mAreaAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1
                //                        , android.R.id.text1, new ArrayList<String>());
                //                dist_lv.setAdapter(mAreaAdapter);
            }
        });
        city_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mArea=null;
                mCity = mCityAddressBeen.get(position);
                String cityCode = mCity.getCode();
                mController.sendAsyncMessage(IDiyMessage.GET_AREA_ACTION, cityCode);

            }
        });
        dist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 mArea= mAreaAddressBeen.get(position);
            }
        });

        mPopWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT
                , WindowManager.LayoutParams.MATCH_PARENT);
        mPopWindow.setContentView(view);
        mPopWindow.setFocusable(true);
        //外部控件无法点击执行操作
        mPopWindow.setOutsideTouchable(true);
        //点击外部消失
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //刷新界面
        mPopWindow.update();
    }

    @Override
    public void onShow(View anchorView) {
        if (mPopWindow != null) {
            mPopWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void onDismiss() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }


    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    public void setListener(IAddressChangeListener listener) {
        mListener = listener;
    }
}
