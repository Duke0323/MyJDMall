package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.github.duke0323.myjdmall.Controller.AlipayController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.AlipayBean;
import io.github.duke0323.myjdmall.bean.PayBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.IPayListener;
import io.github.duke0323.myjdmall.ui.AlipayWindow;

public class AliPayActivity extends BaseActivity implements IModelChangeListener, IPayListener {
    private TextView pay_price_tv;
    private TextView order_desc_val_tv;
    private LinearLayout container;
    private TextView deal_type_val_tv;
    private TextView deal_time_val_tv;
    private TextView deal_no_val_tv;
    private String mTn;
    private AlipayController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.ALIPAY_ACTION_RESULT:
                    handleAlipay((AlipayBean) msg.obj);
                    break;
                case IDiyMessage.PAY_ACTION_RESULT:
                    handlePay((PayBean) msg.obj);
                    break;
            }
        }
    };

    private void handlePay(PayBean bean) {
        if (bean == null) {
            Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        }
        String oid = bean.getOid();
        Toast.makeText(this, oid, Toast.LENGTH_SHORT).show();
        finish();


    }

    private AlipayWindow mAlipayWindow;

    private void handleAlipay(AlipayBean bean) {
        pay_price_tv.setText(String.valueOf("￥" + bean.getTotalPrice()));
        order_desc_val_tv.setText(bean.getPname());

        //后台有问题没有拿到时间
        deal_time_val_tv.setText(bean.getPayTime());
        deal_no_val_tv.setText(bean.getOinfo());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay);
        initController();
        initPassvalues();
        initView();
    }

    @Override
    protected void initController() {
        mController = new AlipayController(this);
        mController.setListener(this);
    }

    private void initPassvalues() {
        if (getIntent() == null) {
            finish();
            return;
        }
        Intent intent = getIntent();
        mTn = intent.getStringExtra(IntentValues.ALIPAYTN);
        mController.sendAsyncMessage(IDiyMessage.ALIPAY_ACTION, mTn);
    }

    private void initView() {
        container = (LinearLayout) findViewById(R.id.container);
        pay_price_tv = (TextView) findViewById(R.id.pay_price_tv);
        order_desc_val_tv = (TextView) findViewById(R.id.order_desc_val_tv);
        deal_type_val_tv = (TextView) findViewById(R.id.deal_type_val_tv);
        deal_time_val_tv = (TextView) findViewById(R.id.deal_time_val_tv);
        deal_no_val_tv = (TextView) findViewById(R.id.deal_no_val_tv);


    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    public void payClick(View view) {
        if (mAlipayWindow == null) {
            mAlipayWindow = new AlipayWindow(this);
            mAlipayWindow.setListener(this);
        }
        mAlipayWindow.onShow(container);
    }

    @Override
    public void onPay(String account, String pwd, String pay_pwd) {
        mController.sendAsyncMessage(IDiyMessage.PAY_ACTION, account, pwd, pay_pwd, mTn);
    }
}
