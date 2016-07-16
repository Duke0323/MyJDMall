package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.github.duke0323.myjdmall.bean.BrandBean;
import io.github.duke0323.myjdmall.bean.CategoryBean;
import io.github.duke0323.myjdmall.bean.CommentBean;
import io.github.duke0323.myjdmall.bean.ProductInfoBean;
import io.github.duke0323.myjdmall.bean.ProductListBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.SProductList;
import io.github.duke0323.myjdmall.bean.SubCategoryBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * 分类页面数据提供者
 * Created by ${Duke} on 2016/7/13.
 */
public class CategoryController extends BaseController {
    public CategoryController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.CATEGORY_TOP_ACTION:
                mListener.onModelChange(IDiyMessage.CATEGORY_TOP_ACTION_RESULT, loadTop(0));
                break;
            case IDiyMessage.CATEGORY_SUB_ACTION:
                CategoryBean bean = (CategoryBean) values[0];
                mListener.onModelChange(IDiyMessage.CATEGORY_SUB_ACTION_RESULT, loadSub(bean.getId()));
                break;
            case IDiyMessage.BRAND_ACTION:
                mListener.onModelChange(IDiyMessage.BRAND_ACTION_RESULT, loadBrand((int) values[0]));
                break;
            case IDiyMessage.PRODUCT_LIST_ACTION:
                mListener.onModelChange(IDiyMessage.PRODUCT_LIST_ACTION_RESULT, loadProductList((SProductList) values[0]));
                break;
            case IDiyMessage.PRODUCT_INFO_PIC_ACTION:
                mListener.onModelChange(IDiyMessage.PRODUCT_INFO_PIC_ACTION_RESULT, loadProductInfo((Long) values[0]));
                break;
            case IDiyMessage.PRODUCT_POSITIVE_ACTION:
                mListener.onModelChange(IDiyMessage.PRODUCT_POSITIVE_ACTION_RESULT, loadProductPositive((Long) values[0]));
                break;
        }
    }

    private List<CommentBean> loadProductPositive(Long value) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("productId", String.valueOf(value));
        params.put("type", String.valueOf(1));
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.PRODUCT_COMMENT_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseArray(rResultBean.getResult(), CommentBean.class);
        }

        return new ArrayList<>();
    }

    private ProductInfoBean loadProductInfo(Long value) {
        String jsonStr = HttpUtils.getInstance().doGet(HttpConst.PRODUCT_INFO_URL + "?id=" + value);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {
            return JSON.parseObject(rResultBean.getResult(), ProductInfoBean.class);
        }
        return new ProductInfoBean();
    }

    private List<ProductListBean> loadProductList(SProductList value) {

        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("categoryId", String.valueOf(value.mCategoryid));
        params.put("filterType", String.valueOf(value.filterType));
        if (value.sortType != 0) {
            params.put("sortType", String.valueOf(value.sortType));
        }
        params.put("deliverChoose", String.valueOf(value.deliverChoose));
        if (value.minPrice != 0 && value.maxPrice != 0) {
            params.put("minPrice", String.valueOf(value.minPrice));
            params.put("maxPrice", String.valueOf(value.maxPrice));
        }
        if (value.brandId != 0) {
            params.put("brandId", String.valueOf(value.brandId));
        }
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.SEARCHPRODUCT_LIST_URL, params);
        RResultBean resultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (resultBean != null && resultBean.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String rows = jsonObject.getString("rows");
                return JSONArray.parseArray(rows, ProductListBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<ProductListBean>();
    }

    //获取品牌信息
    private List<BrandBean> loadBrand(int categoryId) {

        String jsonStr = HttpUtils.getInstance().doGet(HttpConst.BRAND_URL + "?categoryId=" + categoryId);
        RResultBean resultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            return JSON.parseArray(result, BrandBean.class);
        }
        return new ArrayList<BrandBean>();
    }

    private List<CategoryBean> loadTop(int parentId) {
        String url = HttpConst.CATEGORY_URL;
        if (parentId != 0) {
            url = url + "?parentId=" + parentId;
        }
        String jsonStr = HttpUtils.getInstance().doGet(url);

        RResultBean resultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            return JSON.parseArray(result, CategoryBean.class);
        }
        return new ArrayList<CategoryBean>();
    }

    private List<SubCategoryBean> loadSub(int parentId) {
        String url = HttpConst.CATEGORY_URL;
        if (parentId != 0) {
            url = url + "?parentId=" + parentId;
        }
        String jsonStr = HttpUtils.getInstance().doGet(url);

        RResultBean resultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            return JSON.parseArray(result, SubCategoryBean.class);
        }
        return new ArrayList<SubCategoryBean>();
    }
}
