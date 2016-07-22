package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.fragment.AllOrderListFragment;
import io.github.duke0323.myjdmall.fragment.BaseFragemnt;
import io.github.duke0323.myjdmall.fragment.WaitPayFragment;
import io.github.duke0323.myjdmall.fragment.WaitReceiverFragment;
import io.github.duke0323.myjdmall.fragment.WaitSureFragment;

/*
商家发货接口
* http://192.168.33.134:8080/deliver?userId=1&oid=183
*
* */
public class MyOrderActivity extends BaseActivity implements View.OnClickListener {

    private android.view.View allorderview;
    private android.widget.LinearLayout allorderll;
    private android.view.View waitpayview;
    private android.widget.LinearLayout waitpayll;
    private android.view.View waitreceiveview;
    private android.widget.LinearLayout waitreceivell;
    private android.view.View waitsureview;
    private android.widget.LinearLayout waitsurell;
    private android.support.v4.view.ViewPager vp;
    private OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();

    }

    private void initView() {
        this.vp = (ViewPager) findViewById(R.id.vp);
        this.waitsurell = (LinearLayout) findViewById(R.id.wait_sure_ll);
        this.waitsureview = (View) findViewById(R.id.wait_sure_view);
        this.waitreceivell = (LinearLayout) findViewById(R.id.wait_receive_ll);
        this.waitreceiveview = (View) findViewById(R.id.wait_receive_view);
        this.waitpayll = (LinearLayout) findViewById(R.id.wait_pay_ll);
        this.waitpayview = (View) findViewById(R.id.wait_pay_view);
        this.allorderll = (LinearLayout) findViewById(R.id.all_order_ll);
        this.allorderview = (View) findViewById(R.id.all_order_view);
        waitsurell.setOnClickListener(this);
        waitreceivell.setOnClickListener(this);
        waitpayll.setOnClickListener(this);
        allorderll.setOnClickListener(this);
        mAdapter = new OrderAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ChangeBottomStyle(allorderview);
                        break;
                    case 1:
                        ChangeBottomStyle(waitpayview);
                        break;
                    case 2:
                        ChangeBottomStyle(waitreceiveview);
                        break;
                    case 3:
                        ChangeBottomStyle(waitsureview);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class OrderAdapter extends FragmentPagerAdapter {
        ArrayList<BaseFragemnt> fragments = new ArrayList<>();

        public OrderAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new AllOrderListFragment());
            fragments.add(new WaitPayFragment());
            fragments.add(new WaitReceiverFragment());
            fragments.add(new WaitSureFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wait_sure_ll:
                ChangeBottomStyle(waitsureview);
                vp.setCurrentItem(3, true);
                break;
            case R.id.wait_receive_ll:
                ChangeBottomStyle(waitreceiveview);
                vp.setCurrentItem(2, true);
                break;
            case R.id.wait_pay_ll:
                ChangeBottomStyle(waitpayview);
                vp.setCurrentItem(1, true);
                break;
            case R.id.all_order_ll:
                ChangeBottomStyle(allorderview);
                vp.setCurrentItem(0, true);
                break;
        }
    }

    private void ChangeBottomStyle(View v) {
        waitsureview.setVisibility(View.INVISIBLE);
        waitreceiveview.setVisibility(View.INVISIBLE);
        waitpayview.setVisibility(View.INVISIBLE);
        allorderview.setVisibility(View.INVISIBLE);
        v.setVisibility(View.VISIBLE);
    }
}
