package io.github.duke0323.myjdmall.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.Controller.ProductCommentController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.ProductDetailActivity;
import io.github.duke0323.myjdmall.adapter.PositiveCommentAdapter;
import io.github.duke0323.myjdmall.adapter.ProductVersionAdapter;
import io.github.duke0323.myjdmall.bean.CommentBean;
import io.github.duke0323.myjdmall.bean.ProductInfoBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.INumberInputListener;
import io.github.duke0323.myjdmall.ui.NumberInputView;
import io.github.duke0323.myjdmall.utils.FixedViewUtil;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class ProductIntroduceFragment extends BaseFragemnt implements IModelChangeListener, INumberInputListener {
    private ScrollView mScrollview;
    private ViewPager mAsvp;
    private TextView mVpIndicTv;
    private TextView mNameTv;
    private TextView mSelfSaleTv;
    private TextView mDescTv;
    private TextView mRecommendBuyTv;
    private TextView mPriceTv;
    private TextView mTipTv;
    private GridView mProductVersionsGv;
    private TextView mGoodRateTip;
    private TextView mGoodRateTv;
    private TextView mGoodCommentTv;
    private ListView mGoodCommentLv;
    private TextView mScrollToTopIndic;
    private ImageAdapter mImageAdapter;
    private NumberInputView number_input_et;
    private ProductCommentController mController;
    private ProductVersionAdapter mProductVersionAdapter;
    private PositiveCommentAdapter mPositiveCommentAdapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.PRODUCT_INFO_PIC_ACTION_RESULT:
                    handleInfo((ProductInfoBean) msg.obj);
                    break;
                case IDiyMessage.PRODUCT_POSITIVE_ACTION_RESULT:
                    handleComment((List<CommentBean>) msg.obj);
                    break;
            }
        }
    };

    private void handleComment(List<CommentBean> obj) {
        mPositiveCommentAdapter.setDatas(obj);
        mPositiveCommentAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mGoodCommentLv);
    }


    private void handleInfo(ProductInfoBean obj) {
        if (obj == null) {
            Toast.makeText(getContext(), "没有得到产品信息", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            return;
        }
        String imgUrls = obj.getImgUrls();
        handleImage(imgUrls);
        mNameTv.setText(obj.getName());
        if (!obj.isIfSaleOneself()) {
            mSelfSaleTv.setVisibility(View.INVISIBLE);
        }
        mDescTv.setText(obj.getRecomProduct());
        mDescTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        List<String> versions = JSON.parseArray(obj.getTypeList(), String.class);
        mProductVersionAdapter.setDatas(versions);
        mProductVersionAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(mProductVersionsGv, 1);

        number_input_et.setListener(this);
        number_input_et.setMax(obj.getStockCount());

        mGoodRateTv.setText(String.valueOf("好评率" + obj.getFavcomRate() + "%"));
        mGoodCommentTv.setText(String.valueOf(obj.getCommentCount() + "条评论"));


    }

    private void handleImage(String imgUrls) {
        List<String> urls = JSON.parseArray(imgUrls, String.class);
        mImageAdapter.setDatas(urls, getContext());
        String s = 1 + "/" + String.valueOf(mImageAdapter.getCount());
        mVpIndicTv.setText(s);
        mAsvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String s = position + 1 + "/" + String.valueOf(mImageAdapter.getCount());
                mVpIndicTv.setText(s);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mImageAdapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_introduce, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initController();
        assignViews();
    }


    private void initController() {
        mController = new ProductCommentController(getContext());
        mController.setListener(this);
        ProductDetailActivity activity = (ProductDetailActivity) getActivity();
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_INFO_PIC_ACTION, activity.mDetailId);


        mController.sendAsyncMessage(IDiyMessage.PRODUCT_POSITIVE_ACTION, activity.mDetailId);

    }

    private void assignViews() {
        number_input_et = (NumberInputView) getActivity().findViewById(R.id.number_input_et);
        mScrollview = (ScrollView) getActivity().findViewById(R.id.scrollview);
        mAsvp = (ViewPager) getActivity().findViewById(R.id.asvp);
        mVpIndicTv = (TextView) getActivity().findViewById(R.id.vp_indic_tv);
        mNameTv = (TextView) getActivity().findViewById(R.id.name_tv);
        mSelfSaleTv = (TextView) getActivity().findViewById(R.id.self_sale_tv);
        mDescTv = (TextView) getActivity().findViewById(R.id.desc_tv);
        mRecommendBuyTv = (TextView) getActivity().findViewById(R.id.recommend_buy_tv);
        mPriceTv = (TextView) getActivity().findViewById(R.id.price_tv);
        mTipTv = (TextView) getActivity().findViewById(R.id.tip_tv);
        mProductVersionsGv = (GridView) getActivity().findViewById(R.id.product_versions_gv);
        mGoodRateTip = (TextView) getActivity().findViewById(R.id.good_rate_tip);
        mGoodRateTv = (TextView) getActivity().findViewById(R.id.good_rate_tv);
        mGoodCommentTv = (TextView) getActivity().findViewById(R.id.good_comment_tv);
        mGoodCommentLv = (ListView) getActivity().findViewById(R.id.good_comment_lv);
        mScrollToTopIndic = (TextView) getActivity().findViewById(R.id.scroll_to_top_indic);
        mImageAdapter = new ImageAdapter();
        mAsvp.setAdapter(mImageAdapter);
        mProductVersionAdapter = new ProductVersionAdapter(getActivity());
        mProductVersionsGv.setAdapter(mProductVersionAdapter);
        mPositiveCommentAdapter = new PositiveCommentAdapter(getContext());
        mGoodCommentLv.setAdapter(mPositiveCommentAdapter);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onTextChange(int i) {

    }

    public class ImageAdapter extends PagerAdapter {
        List<ImageView> imgs = new ArrayList<>();
        List<String> mList;
        Context mContext;

        public void setDatas(List datas, Context context) {
            mList = datas;
            mContext = context;
            for (int i = 0; i < datas.size(); i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                String adUrl = mList.get(i);
                Picasso.with(mContext).load(HttpConst.DOMAIN + adUrl)
                        .config(Bitmap.Config.RGB_565).into(imageView);
                imgs.add(imageView);
            }

        }

        @Override
        public int getCount() {
            return mList != null ? mList.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imgs.get(position % mList.size());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position % mList.size()));
        }
    }

}
