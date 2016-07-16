package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import io.github.duke0323.myjdmall.Controller.ProductCommentController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.ProductDetailActivity;
import io.github.duke0323.myjdmall.bean.CommentCountBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class ProductCommentFragment extends BaseFragemnt implements View.OnClickListener, IModelChangeListener {
    private LinearLayout mAllCommentLl;
    private TextView mAllCommentTip;
    private TextView mAllCommentTv;
    private LinearLayout mPositiveCommentLl;
    private TextView mPositiveCommentTip;
    private TextView mPositiveCommentTv;
    private LinearLayout mCenterCommentLl;
    private TextView mCenterCommentTip;
    private TextView mCenterCommentTv;
    private LinearLayout mNegativeCommentLl;
    private TextView mNagetiveCommentTip;
    private TextView mNagetiveCommentTv;
    private LinearLayout mHasImageCommentLl;
    private TextView mHasImageCommentTip;
    private TextView mHasImageCommentTv;
    private ListView mLv;
    private ProductCommentController mProductCommentController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.COMMENT_COUNT_ACTION_RESULT:
                    handleCommentCount((CommentCountBean) msg.obj);
                    break;
            }
        }
    };

    private void handleCommentCount(CommentCountBean obj) {
        if (obj != null) {
            mAllCommentTv.setText(String.valueOf(obj.getAllComment()));
            mPositiveCommentTv.setText(String.valueOf(obj.getPositiveCom()));
            mCenterCommentTv.setText(String.valueOf(obj.getModerateCom()));
            mNagetiveCommentTv.setText(String.valueOf(obj.getNegativeCom()));
            mHasImageCommentTv.setText(String.valueOf(obj.getHasImgCom()));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_comment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        assignViews();
        ProductDetailActivity activity = (ProductDetailActivity) getActivity();
        mProductCommentController.sendAsyncMessage(IDiyMessage.COMMENT_COUNT_ACTION, activity.mDetailId);
    }

    private void initController() {
        mProductCommentController = new ProductCommentController(getContext());
        mProductCommentController.setListener(this);
    }


    private void assignViews() {
        mAllCommentLl = (LinearLayout) getActivity().findViewById(R.id.all_comment_ll);
        mAllCommentTip = (TextView) getActivity().findViewById(R.id.all_comment_tip);
        mAllCommentTv = (TextView) getActivity().findViewById(R.id.all_comment_tv);
        mPositiveCommentLl = (LinearLayout) getActivity().findViewById(R.id.positive_comment_ll);
        mPositiveCommentTip = (TextView) getActivity().findViewById(R.id.positive_comment_tip);
        mPositiveCommentTv = (TextView) getActivity().findViewById(R.id.positive_comment_tv);
        mCenterCommentLl = (LinearLayout) getActivity().findViewById(R.id.center_comment_ll);
        mCenterCommentTip = (TextView) getActivity().findViewById(R.id.center_comment_tip);
        mCenterCommentTv = (TextView) getActivity().findViewById(R.id.center_comment_tv);
        mNegativeCommentLl = (LinearLayout) getActivity().findViewById(R.id.negative_comment_ll);
        mNagetiveCommentTip = (TextView) getActivity().findViewById(R.id.nagetive_comment_tip);
        mNagetiveCommentTv = (TextView) getActivity().findViewById(R.id.nagetive_comment_tv);
        mHasImageCommentLl = (LinearLayout) getActivity().findViewById(R.id.has_image_comment_ll);
        mHasImageCommentTip = (TextView) getActivity().findViewById(R.id.has_image_comment_tip);
        mHasImageCommentTv = (TextView) getActivity().findViewById(R.id.has_image_comment_tv);
        mLv = (ListView) getActivity().findViewById(R.id.lv);
        mAllCommentLl.setOnClickListener(this);
        mPositiveCommentLl.setOnClickListener(this);
        mCenterCommentLl.setOnClickListener(this);
        mNegativeCommentLl.setOnClickListener(this);
        mHasImageCommentLl.setOnClickListener(this);
    }

    private void defaultStyle() {
        mAllCommentTip.setSelected(false);
        mAllCommentTv.setSelected(false);
        mPositiveCommentTip.setSelected(false);
        mPositiveCommentTv.setSelected(false);
        mCenterCommentTip.setSelected(false);
        mCenterCommentTv.setSelected(false);
        mNagetiveCommentTip.setSelected(false);
        mNagetiveCommentTv.setSelected(false);
        mHasImageCommentTip.setSelected(false);
        mHasImageCommentTv.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_comment_ll:
                defaultStyle();
                mAllCommentTip.setSelected(true);
                mAllCommentTv.setSelected(true);
                break;
            case R.id.positive_comment_ll:
                defaultStyle();
                mPositiveCommentTip.setSelected(true);
                mPositiveCommentTv.setSelected(true);
                break;
            case R.id.center_comment_ll:
                defaultStyle();
                mCenterCommentTip.setSelected(true);
                mCenterCommentTv.setSelected(true);
                break;
            case R.id.negative_comment_ll:
                defaultStyle();
                mNagetiveCommentTip.setSelected(true);
                mNagetiveCommentTv.setSelected(true);
                break;
            case R.id.has_image_comment_ll:
                defaultStyle();
                mHasImageCommentTip.setSelected(true);
                mHasImageCommentTv.setSelected(true);
                break;
        }
    }


    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }
}
