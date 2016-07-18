package io.github.duke0323.myjdmall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.github.duke0323.myjdmall.R;


public class RatingBar extends LinearLayout {
	
	public RatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	/**
	 * max为 0
	 */
	public void setRating(int count){
		//getchildcount viewgroup 全部子控件设为默认
		for (int i = 0; i < getChildCount(); i++) {
			ImageView iv=(ImageView) getChildAt(i);
			iv.setImageResource(R.drawable.start_normal);
		}
		for (int i = 0; i < count; i++) {
			ImageView iv = (ImageView) getChildAt(i);
			iv.setImageResource(R.drawable.start_selected);
		}
	}

}
