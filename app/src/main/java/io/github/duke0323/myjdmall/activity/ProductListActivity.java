package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.SProductList;
import io.github.duke0323.myjdmall.protocol.IProductSortPopListener;
import io.github.duke0323.myjdmall.ui.ProductSortPopWindow;

public class ProductListActivity extends AppCompatActivity implements IProductSortPopListener, View.OnClickListener {

    private android.widget.TextView allindicator;//综合
    private android.widget.TextView saleindicator;//销量
    private android.widget.TextView priceindicator;//价格
    private android.widget.TextView chooseindicator;//筛选
    private android.widget.ListView productlv;
    private android.widget.LinearLayout contentview;
    private android.widget.TextView jdtaketv;
    private android.widget.TextView paywhenreceivetv;
    private android.widget.TextView justhasstocktv;
    private android.widget.EditText minPriceet;
    private android.widget.EditText maxPriceet;
    private android.widget.GridView gv;
    private android.widget.ScrollView slideview;
    private android.support.v4.widget.DrawerLayout drawerlayout;
    private ProductSortPopWindow mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initView();
    }

    private void initView() {
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        this.slideview = (ScrollView) findViewById(R.id.slide_view);
        this.gv = (GridView) findViewById(R.id.gv);
        this.maxPriceet = (EditText) findViewById(R.id.maxPrice_et);
        this.minPriceet = (EditText) findViewById(R.id.minPrice_et);
        this.justhasstocktv = (TextView) findViewById(R.id.justhasstock_tv);
        this.paywhenreceivetv = (TextView) findViewById(R.id.paywhenreceive_tv);
        this.jdtaketv = (TextView) findViewById(R.id.jd_take_tv);
        this.contentview = (LinearLayout) findViewById(R.id.content_view);
        this.productlv = (ListView) findViewById(R.id.product_lv);
        this.chooseindicator = (TextView) findViewById(R.id.choose_indicator);
        this.priceindicator = (TextView) findViewById(R.id.price_indicator);
        this.saleindicator = (TextView) findViewById(R.id.sale_indicator);
        this.allindicator = (TextView) findViewById(R.id.all_indicator);
        allindicator.setOnClickListener(this);
        saleindicator.setOnClickListener(this);
        priceindicator.setOnClickListener(this);
        chooseindicator.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_indicator:
                changeIndicatorStyle(v);
                if (mPopWindow == null) {
                    mPopWindow = new ProductSortPopWindow(this);
                    mPopWindow.setOnSortChangedListener(this);
                }
                mPopWindow.onShow(allindicator);
                break;
            case R.id.sale_indicator:
                changeIndicatorStyle(v);
                break;
            case R.id.price_indicator:
                changeIndicatorStyle(v);
                break;
            case R.id.choose_indicator:
                //打开抽屉
                drawerlayout.openDrawer(slideview);
                break;
        }
    }

    public void changeIndicatorStyle(View v) {
        allindicator.setSelected(false);
        saleindicator.setSelected(false);
        priceindicator.setSelected(false);

        v.setSelected(true);
    }

    @Override
    public void onSortChanged(int action) {
        switch (action) {
            case SProductList.ALL_SORT:
                allindicator.setText("综合");
                break;
            case SProductList.NEW_SORT:
                allindicator.setText("新品");
                break;
            case SProductList.COMMENT_SORT:
                allindicator.setText("评价");
                break;
        }
    }
}
