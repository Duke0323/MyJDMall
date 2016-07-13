package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.protocol.IBottomBarClickListener;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class BottomBar extends LinearLayout implements View.OnClickListener {
    private LinearLayout frag_main_ll;
    private ImageView frag_main_iv;
    private TextView frag_main;
    private LinearLayout frag_category_ll;
    private ImageView frag_category_iv;
    private TextView frag_category;
    private LinearLayout frag_shopcar_ll;
    private ImageView frag_shopcar_iv;
    private TextView frag_shopcar;
    private LinearLayout frag_mine_ll;
    private ImageView frag_mine_iv;
    private TextView frag_mine;
    private int mCurrentTabId;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private IBottomBarClickListener mListener;

    public void setListener(IBottomBarClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        frag_main_ll = (LinearLayout) findViewById(R.id.frag_main_ll);
        frag_main_iv = (ImageView) findViewById(R.id.frag_main_iv);
        frag_main = (TextView) findViewById(R.id.frag_main);
        frag_main_ll.setOnClickListener(this);

        frag_category_ll = (LinearLayout) findViewById(R.id.frag_category_ll);
        frag_category_iv = (ImageView) findViewById(R.id.frag_category_iv);
        frag_category = (TextView) findViewById(R.id.frag_category);
        frag_category_ll.setOnClickListener(this);

        frag_shopcar_ll = (LinearLayout) findViewById(R.id.frag_shopcar_ll);
        frag_shopcar_iv = (ImageView) findViewById(R.id.frag_shopcar_iv);
        frag_shopcar = (TextView) findViewById(R.id.frag_shopcar);
        frag_shopcar_ll.setOnClickListener(this);

        frag_mine_ll = (LinearLayout) findViewById(R.id.frag_mine_ll);
        frag_mine_iv = (ImageView) findViewById(R.id.frag_mine_iv);
        frag_mine = (TextView) findViewById(R.id.frag_mine);
        frag_mine_ll.setOnClickListener(this);

        frag_main.setSelected(true);
        //模拟点击

        //frag_main_ll.performClick();
        //frag_main_iv.setSelected(true);
        //        frag_category.setSelected(true);
        //        frag_category_iv.setSelected(true);
        //
        //        frag_shopcar.setSelected(true);
        //        frag_shopcar_iv.setSelected(true);
        //        frag_mine.setSelected(true);
        //        frag_mine_iv.setSelected(true);
    }

    @Override
    public void onClick(View view) {
        if (mCurrentTabId != 0 && mCurrentTabId == view.getId()) {
            return;
        }
        mCurrentTabId = view.getId();


        frag_main.setSelected(view.getId() == R.id.frag_main_ll);
        frag_category.setSelected(view.getId() == R.id.frag_category_ll);
        frag_shopcar.setSelected(view.getId() == R.id.frag_shopcar_ll);
        frag_mine.setSelected(view.getId() == R.id.frag_mine_ll);
        if (mListener != null) {
            mListener.onItemClick(view);
        }

    }
}
