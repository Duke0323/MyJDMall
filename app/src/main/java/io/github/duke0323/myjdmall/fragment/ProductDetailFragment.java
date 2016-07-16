package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.ProductDetailActivity;
import io.github.duke0323.myjdmall.config.HttpConst;

/**
 * Created by ${Duke} on 2016/7/15.
 */
public class ProductDetailFragment extends BaseFragemnt {
    private WebView webview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        webview = (WebView) getActivity().findViewById(R.id.webview);
        ProductDetailActivity activity = (ProductDetailActivity) getActivity();
        webview.loadUrl(HttpConst.PRODUCT_DETAIL_URL + "?productId=" + activity.mDetailId);
        //点击内容跳转到新的浏览器
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //启动新的url
                view.loadUrl(url);
                //自己处理
                return true;
            }
        });
        //开启js
        webview.getSettings().setJavaScriptEnabled(true);
    }


}
