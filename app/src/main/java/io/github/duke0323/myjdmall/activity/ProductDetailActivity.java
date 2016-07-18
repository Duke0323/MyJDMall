package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.duke0323.myjdmall.Controller.ProductController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.fragment.BaseFragemnt;
import io.github.duke0323.myjdmall.fragment.ProductCommentFragment;
import io.github.duke0323.myjdmall.fragment.ProductDetailFragment;
import io.github.duke0323.myjdmall.fragment.ProductIntroduceFragment;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, IModelChangeListener {

    private android.view.View detailsview;
    private android.widget.LinearLayout detailsll;
    private android.view.View introduceview;
    private android.widget.LinearLayout introducell;
    private android.view.View commenttv;
    private android.widget.LinearLayout commentll;
    private android.widget.ImageView moreiv;
    private android.support.v4.view.ViewPager vp;
    private ProductAdapter mProductAdapter;
    public long mDetailId;//商品id
    public int mBuyCount = 1;//购买数量
    public String mVersion = "";//版本信息
    private ProductController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.TOSHOPCAR_ACTION_RESULT:
                    handleToShopCar((RResultBean) msg.obj);
                    break;
            }
        }
    };

    private void handleToShopCar(RResultBean obj) {
        String tip = "";
        tip = obj.isSuccess() ? "已添加到购物车" : "系统错误" + obj.getErrorMsg();
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initController();
        initPassValues();
        initView();

    }

    private void initController() {
        mController = new ProductController(this);
        mController.setListener(this);
    }

    private void initPassValues() {
        Intent intent = getIntent();
        if (intent != null) {
            mDetailId = intent.getLongExtra(IntentValues.DETAILID, 0);
        }
        if (mDetailId == 0) {
            Toast.makeText(this, "详情ID错误", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void initView() {
        this.vp = (ViewPager) findViewById(R.id.vp);
        this.moreiv = (ImageView) findViewById(R.id.more_iv);
        this.commentll = (LinearLayout) findViewById(R.id.comment_ll);
        this.commenttv = (View) findViewById(R.id.comment_tv);
        this.introducell = (LinearLayout) findViewById(R.id.introduce_ll);
        this.introduceview = (View) findViewById(R.id.introduce_view);
        this.detailsll = (LinearLayout) findViewById(R.id.details_ll);
        this.detailsview = (View) findViewById(R.id.details_view);
        commentll.setOnClickListener(this);
        introducell.setOnClickListener(this);
        detailsll.setOnClickListener(this);

        mProductAdapter = new ProductAdapter(getSupportFragmentManager());
        vp.setAdapter(mProductAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        changedBottomStyle(introduceview);
                        break;
                    case 1:
                        changedBottomStyle(detailsview);
                        break;
                    case 2:
                        changedBottomStyle(commenttv);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void add2ShopCar(View view) {
        if (mBuyCount <= 0) {
            Toast.makeText(this, "请选择正确数量", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mVersion.equals("")) {
            Toast.makeText(this, "请选择正确的版本", Toast.LENGTH_SHORT).show();
            return;
        }

        mController.sendAsyncMessage(IDiyMessage.TOSHOPCAR_ACTION, mDetailId, mBuyCount, mVersion);

    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    public class ProductAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragemnt> mFragment = new ArrayList<>();

        public ProductAdapter(FragmentManager fm) {
            super(fm);
            //            ProductIntroduceFragment productIntroduceFragment =  new ProductIntroduceFragment();
            //            Bundle introduceBundle = new Bundle();
            //            introduceBundle.putLong(IntentValues.DETAILID,mDetailId);
            //            productIntroduceFragment.setArguments(introduceBundle);
            mFragment.add(new ProductIntroduceFragment());
            mFragment.add(new ProductDetailFragment());
            mFragment.add(new ProductCommentFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_ll:
                changedBottomStyle(commenttv);
                vp.setCurrentItem(2, true);
                break;
            case R.id.introduce_ll:
                changedBottomStyle(introduceview);
                vp.setCurrentItem(0, true);
                break;
            case R.id.details_ll:
                changedBottomStyle(detailsview);
                vp.setCurrentItem(1, true);
                break;
        }
    }

    private void changedBottomStyle(View v) {
        commenttv.setVisibility(View.INVISIBLE);
        introduceview.setVisibility(View.INVISIBLE);
        detailsview.setVisibility(View.INVISIBLE);
        v.setVisibility(View.VISIBLE);
    }

}
