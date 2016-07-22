package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import io.github.duke0323.myjdmall.Controller.AlipayController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.protocol.IPayListener;
import io.github.duke0323.myjdmall.protocol.IPopWindowProtocol;

/**
 * 包装类
 * Created by ${Duke} on 2016/7/14.
 */
public class AlipayWindow implements IPopWindowProtocol, View.OnClickListener {
    Context mContext;
    PopupWindow mPopWindow;
    private AlipayController mController;
    private EditText account_et;
    private EditText pwd_et;
    private EditText pay_pwd_et;
    private Button cancel_btn;
    private Button pay_btn;
    private IPayListener mListener;

    public AlipayWindow(Context context) {
        mContext = context;

        initView();
    }


    @Override
    public void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pay_dialog, null);

        account_et = (EditText) view.findViewById(R.id.account_et);
        pwd_et = (EditText) view.findViewById(R.id.pwd_et);
        pay_pwd_et = (EditText) view.findViewById(R.id.pay_pwd_et);
        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        pay_btn = (Button) view.findViewById(R.id.pay_btn);
        cancel_btn.setOnClickListener(this);
        pay_btn.setOnClickListener(this);


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
    public void onClick(View v) {
        onDismiss();
        switch (v.getId()) {
            case R.id.cancel_btn:
                Toast.makeText(mContext, "取消支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pay_btn:
                pay();
                break;
        }
    }

    private void pay() {
        String account = account_et.getText().toString().trim();
        String pwd = pwd_et.getText().toString().trim();
        String pay_pwd = pay_pwd_et.getText().toString().trim();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pay_pwd) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(mContext, "请输入完整信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mListener!=null) {
            mListener.onPay(account, pwd, pay_pwd);
        }
    }

    public void setListener(IPayListener listener) {
        mListener = listener;
    }
}
