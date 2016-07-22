package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.BuildConfig;
import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.bean.RBuildOrderParams;
import io.github.duke0323.myjdmall.bean.SBuildOrderParams;
import io.github.duke0323.myjdmall.bean.SBuildOrderProductBean;
import io.github.duke0323.myjdmall.bean.ShopListBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.AddOrderWindow;

public class SettleActivity extends BaseActivity implements IModelChangeListener, View.OnClickListener {
    private RelativeLayout has_receiver_rl;
    private RelativeLayout no_receiver_rl;
    private LinearLayout settle;
    private TextView name;
    private TextView phone;
    private TextView address;

    private ArrayList<ShopListBean> mInfo;
    private ShopCarController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.RECEIVE_ADDRESS_ACTION_RESULT:
                    handleAddress((List<AddressBean>) msg.obj);
                    break;
                case IDiyMessage.ADD_ORDER_ACTION_RESULT:
                    addOrder((RBuildOrderParams) msg.obj);
                    break;
            }
        }
    };

    private void addOrder(RBuildOrderParams obj) {
        //弹出popwindow
        if (obj != null) {
            mWindow = new AddOrderWindow(this);
            mWindow.setDatas(obj);
            mWindow.onShow(settle);
            for (int i = 0; i < mInfo.size(); i++) {
                int id = mInfo.get(i).getId();
                mController.sendAsyncMessage(IDiyMessage.DELETE_SHOPCAR_ACTION, id);

            }


        } else {
            Toast.makeText(this, "创建订单失败", Toast.LENGTH_SHORT).show();
        }
    }

    private AddOrderWindow mWindow;


    private double mTotalPrice;


    private void handleAddress(List<AddressBean> bean) {
        if (bean.size() > 0) {
            AddressBean addressBean = bean.get(0);
            has_receiver_rl.setVisibility(View.VISIBLE);
            has_receiver_rl.setTag(addressBean.getId());
            name.setText(addressBean.getReceiverName());
            phone.setText(addressBean.getReceiverPhone());
            address.setText(addressBean.getReceiverAddress());
        } else {
            no_receiver_rl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        initPassValues();
        initController();
        initView();
        mController.sendAsyncMessage(IDiyMessage.RECEIVE_ADDRESS_ACTION, true);
    }


    private void initPassValues() {
        Intent intent = getIntent();
        if (intent != null) {

            mInfo = intent.getParcelableArrayListExtra(IntentValues.SUBMITINFO);

            if (mInfo == null || mInfo.size() == 0) {
                if (BuildConfig.DEBUG)
                    Log.d("SettleActivity", "null");
                Toast.makeText(this, "商品加载错误", Toast.LENGTH_SHORT).show();
                finish();
            }

            mTotalPrice = intent.getDoubleExtra(IntentValues.PRODUCTTOTALPRICE, 0);


        }
    }

    public void initController() {
        mController = new ShopCarController(this);
        mController.setListener(this);
    }

    private TextView all_price_val_tv;
    private TextView pay_money_tv;
    private TextView total_psize_tv;
    private Button pay_online_tv;
    private Button pay_whenget_tv;

    private void initView() {
        has_receiver_rl = (RelativeLayout) findViewById(R.id.has_receiver_rl);
        settle = (LinearLayout) findViewById(R.id.settle);
        no_receiver_rl = (RelativeLayout) findViewById(R.id.no_receiver_rl);
        name = (TextView) findViewById(R.id.name_tv);
        phone = (TextView) findViewById(R.id.phone_tv);
        address = (TextView) findViewById(R.id.address_tv);
        initProductContainer();
        all_price_val_tv = (TextView) findViewById(R.id.all_price_val_tv);
        pay_money_tv = (TextView) findViewById(R.id.pay_money_tv);

        all_price_val_tv.setText(String.valueOf("￥" + mTotalPrice));
        pay_money_tv.setText(String.valueOf("实付 ￥" + mTotalPrice));

        pay_online_tv = (Button) findViewById(R.id.pay_online_tv);
        pay_online_tv.setOnClickListener(this);
        pay_whenget_tv = (Button) findViewById(R.id.pay_whenget_tv);
        pay_whenget_tv.setOnClickListener(this);

    }

    LinearLayout product_container_ll;

    //产品图片的显示
    private void initProductContainer() {
        product_container_ll = (LinearLayout) findViewById(R.id.product_container_ll);
        total_psize_tv = (TextView) findViewById(R.id.total_psize_tv);
        int result = 0;
        if (product_container_ll != null) {
            int childCount = product_container_ll.getChildCount();
            int size = mInfo.size();
            int count = Math.min(childCount, size);
            for (int i = 0; i < count; i++) {
                LinearLayout childLl = (LinearLayout) product_container_ll.getChildAt(i);
                ImageView pImg = (ImageView) childLl.findViewById(R.id.piv);
                TextView pText = (TextView) childLl.findViewById(R.id.psize);
                Picasso.with(this).load(HttpConst.DOMAIN + mInfo.get(i).getPimageUrl())
                        .config(Bitmap.Config.RGB_565).into(pImg);
                pText.setText(String.valueOf(" X" + mInfo.get(i).getBuyCount()));
                result += mInfo.get(i).getBuyCount();
            }
            total_psize_tv.setText("共" + mInfo.size() + "种" + result + "件");
        }
    }


    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    public static int CHOOSEADDRESS = 1;
    public static int ADDADDRESS = 0;

    public void chooseAddress(View view) {
        Intent intent = new Intent(this, ChooseAddressActivity.class);
        startActivityForResult(intent, CHOOSEADDRESS);
    }

    public void addAddress(View view) {
        Intent intent = new Intent(this, AddNewAddressActivity.class);
        startActivityForResult(intent, ADDADDRESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == CHOOSEADDRESS) {
                AddressBean choAddressBean = data.getParcelableExtra(IntentValues.CHOOSEADDRESS);

                has_receiver_rl.setVisibility(View.VISIBLE);
                has_receiver_rl.setTag(choAddressBean.getId());
                no_receiver_rl.setVisibility(View.GONE);
                name.setText(choAddressBean.getReceiverName());
                phone.setText(choAddressBean.getReceiverPhone());
                address.setText(choAddressBean.getReceiverAddress());
            }
        } else if (requestCode == ADDADDRESS) {
            AddressBean addressBean = data.getParcelableExtra(IntentValues.ADDADDRESS);
            has_receiver_rl.setVisibility(View.VISIBLE);
            has_receiver_rl.setTag(addressBean.getId());
            no_receiver_rl.setVisibility(View.INVISIBLE);
            name.setText(addressBean.getReceiverName());
            phone.setText(addressBean.getReceiverPhone());
            address.setText(addressBean.getReceiverAddress());
        } else {
            has_receiver_rl.setVisibility(View.GONE);
            no_receiver_rl.setVisibility(View.VISIBLE);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

        pay_online_tv.setSelected(v.getId() == R.id.pay_online_tv);
        pay_whenget_tv.setSelected(v.getId() == R.id.pay_whenget_tv);


    }

    //提交订单
    public void submitClick(View view) {
        if (has_receiver_rl.getVisibility() != View.VISIBLE) {
            Toast.makeText(this, "请选择收货人", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mInfo == null || mInfo.size() <= 0) {
            Toast.makeText(this, "没有商品", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pay_online_tv.isSelected() && !pay_whenget_tv.isSelected()) {
            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
            return;
        }
        //
        int id = (int) has_receiver_rl.getTag();
        SBuildOrderParams sBuildOrderParams = new SBuildOrderParams();
        sBuildOrderParams.setAddrId(id);
        int payWay = -1;
        if (pay_online_tv.isSelected()) {
            payWay = 0;
        } else if (pay_whenget_tv.isSelected()) {
            payWay = 1;
        }
        if (payWay == 0 || payWay == 1) {
            sBuildOrderParams.setPayWay(payWay);
        }
        ArrayList<SBuildOrderProductBean> bean = new ArrayList<>();
        for (int i = 0; i < mInfo.size(); i++) {
            ShopListBean shopListBean = mInfo.get(i);
            bean.add(new SBuildOrderProductBean(
                    shopListBean.getBuyCount(), shopListBean.getPversion(), shopListBean.getPid()));
        }
        sBuildOrderParams.setProducts(bean);
        Logger.d(sBuildOrderParams.toString());
        mController.sendAsyncMessage(IDiyMessage.ADD_ORDER_ACTION, sBuildOrderParams);
    }


}
