package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.CategoryController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.BrandAdapter;
import io.github.duke0323.myjdmall.adapter.ProductListAdapter;
import io.github.duke0323.myjdmall.bean.BrandBean;
import io.github.duke0323.myjdmall.bean.ProductListBean;
import io.github.duke0323.myjdmall.bean.SProductList;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.IProductSortPopListener;
import io.github.duke0323.myjdmall.ui.ProductSortPopWindow;
import io.github.duke0323.myjdmall.utils.FixedViewUtil;

public class ProductListActivity extends BaseActivity implements IProductSortPopListener, View.OnClickListener, IModelChangeListener, AdapterView.OnItemClickListener {

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
    private BrandAdapter mAdapter;
    private int mCategoryid;//三级id
    private int mTopcategoryid;//一级id
    private CategoryController mController;
    private SProductList mProductList;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.BRAND_ACTION_RESULT:
                    handleBrand((List<BrandBean>) msg.obj);
                    break;
                case IDiyMessage.PRODUCT_LIST_ACTION_RESULT:
                    handleProduct((List<ProductListBean>) msg.obj);
                    break;
            }
        }
    };
    private ProductListAdapter mProductListAdapter;

    private void handleProduct(List<ProductListBean> obj) {

        mProductListAdapter.setDatas(obj);
        mProductListAdapter.notifyDataSetChanged();


    }

    //显示brand
    private void handleBrand(List<BrandBean> obj) {
        if (obj.size() > 0) {
            mAdapter.setDatas(obj);
            mAdapter.notifyDataSetChanged();
            //嵌套冲突处理  计算gridview高度定死
            FixedViewUtil.setListViewHeightBasedOnChildren(gv, 3);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initPassValue();
        initController();
        initView();
        initSliderView();
        mController.sendAsyncMessage(IDiyMessage.BRAND_ACTION, mTopcategoryid);
        //商品列表
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);
    }

    @Override
    protected void initController() {
        mController = new CategoryController(this);
        mController.setListener(this);
    }

    //获取传递来的数值
    private void initPassValue() {
        Intent intent = getIntent();
        if (intent != null) {
            //三级分类id
            mCategoryid = intent.getIntExtra(IntentValues.CATEGORYID, 0);
            //一级分类id
            mTopcategoryid = intent.getIntExtra(IntentValues.TOPCATEGORYID, 0);
            if (mCategoryid == 0 || mTopcategoryid == 0) {
                Toast.makeText(this, "获取分类信息错误", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        mProductList = new SProductList();
        mProductList.mCategoryid = mCategoryid;
        mProductList.filterType = 1;

    }

    private void initView() {
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        this.slideview = (ScrollView) findViewById(R.id.slide_view);
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
        productlv.setOnItemClickListener(this);
        mProductListAdapter = new ProductListAdapter(this);
        productlv.setAdapter(mProductListAdapter);
    }

    private void initSliderView() {
        this.maxPriceet = (EditText) findViewById(R.id.maxPrice_et);
        this.minPriceet = (EditText) findViewById(R.id.minPrice_et);
        this.justhasstocktv = (TextView) findViewById(R.id.justhasstock_tv);
        this.paywhenreceivetv = (TextView) findViewById(R.id.paywhenreceive_tv);
        this.jdtaketv = (TextView) findViewById(R.id.jd_take_tv);
        justhasstocktv.setOnClickListener(this);
        paywhenreceivetv.setOnClickListener(this);
        jdtaketv.setOnClickListener(this);
        //商品分类gridview
        this.gv = (GridView) findViewById(R.id.gv);
        mAdapter = new BrandAdapter(this);
        gv.setAdapter(mAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_indicator://综合
                changeIndicatorStyle(v);
                if (mPopWindow == null) {
                    mPopWindow = new ProductSortPopWindow(this);
                    mPopWindow.setOnSortChangedListener(this);
                }
                mPopWindow.onShow(allindicator);
                break;
            case R.id.sale_indicator://销量
                changeIndicatorStyle(v);
                mProductList.sortType = SProductList.SALE_SORT;
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);
                break;
            case R.id.price_indicator://价格
                changeIndicatorStyle(v);
                //sorttype销量默认.  或者选销量 或者价格从高到低
                if (mProductList.sortType == 0 || mProductList.sortType == SProductList.SALE_SORT
                        || mProductList.sortType == SProductList.PRICE_UP2DOWN) {
                    mProductList.sortType = SProductList.PRICE_DOWN2UP;
                    //修该价格旁边的图片指示
                    v.setSelected(true);
                    //sorttype销量默认.  或者选销量 或者价格从低到高
                } else if (mProductList.sortType == 0 || mProductList.sortType == SProductList.SALE_SORT
                        || mProductList.sortType == SProductList.PRICE_DOWN2UP) {
                    mProductList.sortType = SProductList.PRICE_UP2DOWN;
                    v.setSelected(false);
                }
                mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);
                break;
            case R.id.choose_indicator://筛选
                //打开抽屉
                drawerlayout.openDrawer(slideview);
                break;
            case R.id.justhasstock_tv://仅限有货
                v.setSelected(!v.isSelected());

                break;
            case R.id.paywhenreceive_tv://货到付款
                v.setSelected(!v.isSelected());
                break;
            case R.id.jd_take_tv://京东配送
                v.setSelected(!v.isSelected());
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
                mProductList.filterType = 1;
                break;
            case SProductList.NEW_SORT:
                allindicator.setText("新品");
                mProductList.filterType = 2;
                break;
            case SProductList.COMMENT_SORT:
                allindicator.setText("评价");
                mProductList.filterType = 3;
                break;
        }
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    //确定按钮
    public void chooseSearchClick(View v) {

        int deliverChoose = 0;
        if (jdtaketv.isSelected()) {
            deliverChoose += 1;
        }
        if (paywhenreceivetv.isSelected()) {
            deliverChoose += 2;
        }
        if (justhasstocktv.isSelected()) {
            deliverChoose += 4;
        }
        mProductList.deliverChoose = deliverChoose;
        String minPrice = minPriceet.getText().toString().trim();
        String maxPrice = maxPriceet.getText().toString().trim();
        if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
            mProductList.minPrice = Double.parseDouble(minPrice);
            mProductList.maxPrice = Double.parseDouble(maxPrice);
        }
        //当前选中的位置的对象
        int tabPosition = mAdapter.mTabPosition;
        if (tabPosition != -1) {
            BrandBean bean = (BrandBean) mAdapter.getItem(tabPosition);
            mProductList.brandId = bean.getId();
        }
        drawerlayout.closeDrawer(slideview);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);

    }

    //重置按钮
    public void resetClick(View v) {
        mProductList.filterType = SProductList.ALL_SORT;
        mProductList.sortType = 0;
        mProductList.deliverChoose = 0;
        mProductList.minPrice = 0;
        mProductList.maxPrice = 0;
        mProductList.brandId = 0;
        drawerlayout.closeDrawer(slideview);
        mController.sendAsyncMessage(IDiyMessage.PRODUCT_LIST_ACTION, mProductList);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //传递商品id给详情页
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(IntentValues.DETAILID, mProductListAdapter.getItemId(position));
        startActivity(intent);
        finish();
    }
}
