package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.ProductListActivity;
import io.github.duke0323.myjdmall.bean.SProductList;
import io.github.duke0323.myjdmall.protocol.IPopWindowProtocol;
import io.github.duke0323.myjdmall.protocol.IProductSortPopListener;

/**
 * 包装类
 * Created by ${Duke} on 2016/7/14.
 */
public class ProductSortPopWindow implements IPopWindowProtocol, View.OnClickListener {
    Context mContext;
    PopupWindow mPopWindow;
    private android.widget.TextView allsort;
    private android.widget.TextView newsort;
    private android.widget.TextView commentsort;
    private View leftv;
    private IProductSortPopListener mOnSortChangedListener;

    public ProductSortPopWindow(Context context) {
        mContext = context;
        initView();

    }

    @Override
    public void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null);
        this.leftv = (View) view.findViewById(R.id.left_v);
        this.commentsort = (TextView) view.findViewById(R.id.comment_sort);
        this.newsort = (TextView) view.findViewById(R.id.new_sort);
        this.allsort = (TextView) view.findViewById(R.id.all_sort);
        commentsort.setOnClickListener(this);
        newsort.setOnClickListener(this);
        allsort.setOnClickListener(this);
        leftv.setOnClickListener(this);


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

            mPopWindow.showAsDropDown(anchorView);
        }
    }

    @Override
    public void dismiss() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.comment_sort:
                if (mOnSortChangedListener != null) {
                    mOnSortChangedListener.onSortChanged(SProductList.COMMENT_SORT);
                }
                break;
            case R.id.new_sort:
                if (mOnSortChangedListener != null) {
                    mOnSortChangedListener.onSortChanged(SProductList.NEW_SORT);
                }
                break;
            case R.id.all_sort:
                if (mOnSortChangedListener != null) {
                    mOnSortChangedListener.onSortChanged(SProductList.ALL_SORT);
                }
                break;
        }
    }

    public void setOnSortChangedListener(ProductListActivity onSortChangedListener) {
        mOnSortChangedListener = onSortChangedListener;
    }
}
