package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.github.duke0323.myjdmall.Controller.HomeController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.AdsAdapter;
import io.github.duke0323.myjdmall.bean.AdsResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.HorizontalListView;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class HomeFragment extends Fragment implements IModelChangeListener {
    private ImageView mScanIv;
    private EditText mSearchEt;
    private ImageView mMessageIv;
    private ScrollView mScrollbar;
    private LinearLayout mLl;
    private RelativeLayout mAdRl;
    private ViewPager mAdVp;
    private LinearLayout mAdIndicator;
    private ImageView mAd2Iv;
    private ImageView mClock;
    private TextView mSeckillTipTv;
    private TextView mSeckillTv;
    private HorizontalListView mHorizonListview;
    private GridView mRecommendGv;
    private HomeController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.HOME_ADS_ACTION_RESULT:
                    handleAds((List<AdsResultBean>) msg.obj);

                    break;
                case 0X009:
                    mAdVp.setCurrentItem(mAdVp.getCurrentItem() + 1, true);
                    break;
            }
        }
    };
    private AdsAdapter mAdsAdapter;
    private Timer mTimer;

    private void handleAds(final List<AdsResultBean> obj) {

        final ArrayList<ImageView> mDots_array = new ArrayList<>();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);

            ImageView dotsImg = new ImageView(getActivity());
            dotsImg.setImageResource(R.drawable.head_bottom_dot_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(9, 0, 0, 0);
            mDots_array.add(dotsImg);
            mAdIndicator.addView(dotsImg, params);
        }
        //mAdVp.setCurrentItem((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % obj.size());
        mDots_array.get(0).setImageResource(R.drawable.head_bottom_dot_white);
        AdsAdapter adsAdapter = new AdsAdapter(imageViews, obj, getActivity());
        mAdVp.setAdapter(adsAdapter);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.obtainMessage(0X009).sendToTarget();
            }
        }, 3000);


        //adsAdapter.notifyDataSetChanged();
        //        mAdsAdapter.setDatas( obj, getActivity());
        //        mAdsAdapter.notifyDataSetChanged();

        mAdVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int nowPosition = position % obj.size();
                for (int i = 0; i < mDots_array.size(); i++) {
                    ImageView imageView = mDots_array.get(i);
                    if (i == nowPosition) {
                        imageView.setImageResource(R.drawable.head_bottom_dot_white);
                    } else {
                        imageView.setImageResource(R.drawable.head_bottom_dot_gray);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        assignViews();
        initController();
        //        mAdsAdapter = new AdsAdapter();
        //        mAdVp.setAdapter(mAdsAdapter);
    }

    private void initController() {
        mController = new HomeController(getActivity());
        mController.setListener(this);
        mController.sendAsyncMessage(IDiyMessage.HOME_ADS_ACTION, 0);

    }

    private void assignViews() {
        mScanIv = (ImageView) getActivity().findViewById(R.id.scan_iv);
        mSearchEt = (EditText) getActivity().findViewById(R.id.search_et);
        mMessageIv = (ImageView) getActivity().findViewById(R.id.message_iv);
        mScrollbar = (ScrollView) getActivity().findViewById(R.id.scrollbar);
        mLl = (LinearLayout) getActivity().findViewById(R.id.ll);
        mAdRl = (RelativeLayout) getActivity().findViewById(R.id.ad_rl);
        mAdVp = (ViewPager) getActivity().findViewById(R.id.ad_vp);
        mAdIndicator = (LinearLayout) getActivity().findViewById(R.id.ad_indicator);
        mAd2Iv = (ImageView) getActivity().findViewById(R.id.ad2_iv);
        mClock = (ImageView) getActivity().findViewById(R.id.clock);
        mSeckillTipTv = (TextView) getActivity().findViewById(R.id.seckill_tip_tv);
        mSeckillTv = (TextView) getActivity().findViewById(R.id.seckill_tv);
        mHorizonListview = (HorizontalListView) getActivity().findViewById(R.id.horizon_listview);
        mRecommendGv = (GridView) getActivity().findViewById(R.id.recommend_gv);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTimer.cancel();
    }
}
