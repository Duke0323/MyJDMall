package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.duke0323.myjdmall.R;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class ProductDetailFragment extends BaseFragemnt {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, null);
    }


}