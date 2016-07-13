package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.fragment.CategoryFragment;
import io.github.duke0323.myjdmall.fragment.HomeFragment;
import io.github.duke0323.myjdmall.fragment.MIneFragment;
import io.github.duke0323.myjdmall.fragment.ShopCarFragment;
import io.github.duke0323.myjdmall.protocol.IBottomBarClickListener;
import io.github.duke0323.myjdmall.ui.BottomBar;

public class MainActivity extends FragmentActivity implements IBottomBarClickListener {

    private android.widget.FrameLayout topbar;
    private BottomBar bottom_bar;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private ShopCarFragment mShopCarFragment;
    private MIneFragment mMIneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.topbar = (FrameLayout) findViewById(R.id.top_bar);
        bottom_bar = (BottomBar) findViewById(R.id.bottom_bar);
        if (bottom_bar != null) {
            //持有controller
            bottom_bar.setListener(this);
        }
        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mShopCarFragment = new ShopCarFragment();
        mMIneFragment = new MIneFragment();
        replaceTopBarChild(mHomeFragment);
    }

    private void replaceTopBarChild(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(R.id.top_bar, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onItemClick(View view) {

        switch (view.getId()) {
            case R.id.frag_main_ll:
                replaceTopBarChild(mHomeFragment);
                break;
            case R.id.frag_category_ll:
                replaceTopBarChild(mCategoryFragment);
                break;
            case R.id.frag_shopcar_ll:
                replaceTopBarChild(mShopCarFragment);
                break;
            case R.id.frag_mine_ll:
                replaceTopBarChild(mMIneFragment);
                break;
        }
    }
}
