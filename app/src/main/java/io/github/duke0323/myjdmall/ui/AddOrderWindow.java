package io.github.duke0323.myjdmall.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.AliPayActivity;
import io.github.duke0323.myjdmall.bean.RBuildOrderParams;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IPopWindowProtocol;

/**
 * 包装类
 * Created by ${Duke} on 2016/7/14.
 */
public class AddOrderWindow implements IPopWindowProtocol, View.OnClickListener {
    Context mContext;
    PopupWindow mPopWindow;
    private TextView title_tv;
    private LinearLayout info_ll;
    private TextView order_no_tv;
    private TextView total_price_tv;
    private TextView freight_tv;
    private TextView actual_price_tv;
    private Button cancal_btn;
    private Button sure_btn;
    private RBuildOrderParams mParams;


    public AddOrderWindow(Context context) {
        mContext = context;
        initView();

    }

    @Override
    public void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.build_order_pop_view, null);
        this.info_ll = (LinearLayout) view.findViewById(R.id.info_ll);
        this.title_tv = (TextView) view.findViewById(R.id.title_tv);
        this.order_no_tv = (TextView) view.findViewById(R.id.order_no_tv);
        this.total_price_tv = (TextView) view.findViewById(R.id.total_price_tv);
        this.freight_tv = (TextView) view.findViewById(R.id.freight_tv);
        this.actual_price_tv = (TextView) view.findViewById(R.id.actual_price_tv);
        this.cancal_btn = (Button) view.findViewById(R.id.cancal_btn);
        this.sure_btn = (Button) view.findViewById(R.id.sure_btn);
        cancal_btn.setOnClickListener(this);
        sure_btn.setOnClickListener(this);


        mPopWindow = new PopupWindow(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT);
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
            //显示在中间
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
            case R.id.cancal_btn:
                Toast.makeText(mContext, "取消支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sure_btn:
                Toast.makeText(mContext, "跳转支付", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, AliPayActivity.class);
                intent.putExtra(IntentValues.ALIPAYTN, mParams.getTn());
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
                break;
        }
    }


    public void setDatas(RBuildOrderParams obj) {
        mParams = obj;
        freight_tv.setText(String.valueOf("运费 ￥" + obj.getFreight()));
        order_no_tv.setText("订单号:" + obj.getOrderNum());
        total_price_tv.setText(String.valueOf("总价 ￥" + obj.getTotalPrice()));
        actual_price_tv.setText(String.valueOf("实付 ￥" + obj.getAllPrice()));
    }
}
