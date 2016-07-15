package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.fragment.BaseFragemnt;
import io.github.duke0323.myjdmall.fragment.ProductCommentFragment;
import io.github.duke0323.myjdmall.fragment.ProductDetailFragment;
import io.github.duke0323.myjdmall.fragment.ProductIntroduceFragment;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private android.view.View detailsview;
    private android.widget.LinearLayout detailsll;
    private android.view.View introduceview;
    private android.widget.LinearLayout introducell;
    private android.view.View commenttv;
    private android.widget.LinearLayout commentll;
    private android.widget.ImageView moreiv;
    private android.support.v4.view.ViewPager vp;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
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

    public class ProductAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragemnt> mFragment = new ArrayList<>();

        public ProductAdapter(FragmentManager fm) {
            super(fm);
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
