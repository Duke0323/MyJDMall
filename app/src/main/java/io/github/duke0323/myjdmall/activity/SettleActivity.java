package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.utils.ActivityUtils;

public class SettleActivity extends AppCompatActivity implements IModelChangeListener {
    private RelativeLayout has_receiver_rl;
    private RelativeLayout no_receiver_rl;
    private TextView name;
    private TextView phone;
    private TextView address;
    private ShopCarController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.RECEIVE_ADDRESS_ACTION_RESULT:
                    handleAddress((List<AddressBean>) msg.obj);
                    break;
            }
        }
    };

    private void handleAddress(List<AddressBean> bean) {
        if (bean.size() > 0) {
            has_receiver_rl.setVisibility(View.VISIBLE);
            AddressBean addressBean = bean.get(0);
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
        initController();
        initView();
        mController.sendAsyncMessage(IDiyMessage.RECEIVE_ADDRESS_ACTION, true);
    }

    private void initController() {
        mController = new ShopCarController(this);
        mController.setListener(this);
    }

    private void initView() {
        has_receiver_rl = (RelativeLayout) findViewById(R.id.has_receiver_rl);
        no_receiver_rl = (RelativeLayout) findViewById(R.id.no_receiver_rl);
        name = (TextView) findViewById(R.id.name_tv);
        phone = (TextView) findViewById(R.id.phone_tv);
        address = (TextView) findViewById(R.id.address_tv);


    }


    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    public void chooseAddress(View view) {
        ActivityUtils.start(this, ChooseAddressActivity.class, false);
    }

    public void addAddress(View view) {
        Intent intent = new Intent(this, AddNewAddressActivity.class);
        startActivityForResult(intent,0);
    }
}
