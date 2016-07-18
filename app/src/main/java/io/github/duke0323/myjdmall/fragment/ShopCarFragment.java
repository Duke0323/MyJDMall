package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.SettleActivity;
import io.github.duke0323.myjdmall.adapter.ShopCarAdapter;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.ShopListBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.IShopCarDeleteListener;
import io.github.duke0323.myjdmall.utils.ActivityUtils;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class ShopCarFragment extends BaseFragemnt implements IModelChangeListener
        , AdapterView.OnItemClickListener
        , CompoundButton.OnCheckedChangeListener
        , IShopCarDeleteListener, View.OnClickListener {
    private ListView mShopcarLv;
    private ImageView null_image;
    private CheckBox mAllCbx;
    private TextView mAllMoneyTv;
    private TextView mSettleTv;
    private ShopCarAdapter mShopCarAdapter;
    private ShopCarController mShopCarController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.LOAD_SHOPCAR_ACTION_RESULT:
                    handleShopList((List<ShopListBean>) msg.obj);
                    break;
                case IDiyMessage.DELETE_SHOPCAR_ACTION_RESULT:
                    handleDelete((RResultBean) msg.obj);
                    break;
            }
        }
    };

    //处理删除
    private void handleDelete(RResultBean bean) {
        if (bean.isSuccess()) {
            mShopCarController.sendAsyncMessage(IDiyMessage.LOAD_SHOPCAR_ACTION, 0);
            mAllMoneyTv.setText(String.valueOf("总金额 ￥ 0.0"));
            mSettleTv.setText(String.valueOf("去结算(0)"));

        } else {
            Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleShopList(List<ShopListBean> bean) {
        mShopCarAdapter.setDatas(bean);
        mShopCarAdapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopcar, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        assignViews();
        mShopCarAdapter = new ShopCarAdapter(getContext());
        mShopCarAdapter.setDeleteListener(this);
        mShopcarLv.setAdapter(mShopCarAdapter);
        mShopcarLv.setOnItemClickListener(this);
        //userid全局的后台自己获取就可以了
        mShopCarController.sendAsyncMessage(IDiyMessage.LOAD_SHOPCAR_ACTION, 0);
    }

    private void initController() {
        mShopCarController = new ShopCarController(getContext());
        mShopCarController.setListener(this);

    }


    private void assignViews() {
        mShopcarLv = (ListView) getActivity().findViewById(R.id.shopcar_lv);
        null_image = (ImageView) getActivity().findViewById(R.id.null_view).findViewById(R.id.null_image);
        mAllCbx = (CheckBox) getActivity().findViewById(R.id.all_cbx);
        mAllMoneyTv = (TextView) getActivity().findViewById(R.id.all_money_tv);
        mSettleTv = (TextView) getActivity().findViewById(R.id.settle_tv);
        mAllCbx.setOnCheckedChangeListener(this);
        mSettleTv.setOnClickListener(this);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mShopCarAdapter.setItemSeleted(position);
        mShopCarAdapter.notifyDataSetChanged();


        mAllMoneyTv.setText(String.valueOf("总金额 ￥" + mShopCarAdapter.getSelectedTotalPrice()));
        mSettleTv.setText(String.valueOf("去结算(" + mShopCarAdapter.getSelectedCount() + ")"));

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mShopCarAdapter.setSelectedAllItems(isChecked);
        mShopCarAdapter.notifyDataSetChanged();
        mAllMoneyTv.setText(String.valueOf("总金额 ￥" + mShopCarAdapter.getSelectedTotalPrice()));
        mSettleTv.setText(String.valueOf("去结算(" + mShopCarAdapter.getSelectedCount() + ")"));
    }


    @Override
    public void onDeleteItem(int item) {
        mShopCarController.sendAsyncMessage(IDiyMessage.DELETE_SHOPCAR_ACTION, item);
    }

    @Override
    public void onClick(View v) {
        if (mShopCarAdapter.getSelectedCount() <= 0) {
            Toast.makeText(getActivity(), "请选择商品再提交订单", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityUtils.start(getActivity(), SettleActivity.class, false);
    }
}
