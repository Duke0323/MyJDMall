package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.github.duke0323.myjdmall.bean.CommentBean;
import io.github.duke0323.myjdmall.bean.CommentCountBean;
import io.github.duke0323.myjdmall.bean.ProductInfoBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.utils.HttpUtils;

/**
 * Created by ${Duke} on 2016/7/16.
 */
public class ProductCommentController extends BaseController {


    public ProductCommentController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.PRODUCT_INFO_PIC_ACTION:
                mListener.onModelChange(IDiyMessage.PRODUCT_INFO_PIC_ACTION_RESULT, loadProductInfo((Long) values[0]));
                break;
            case IDiyMessage.PRODUCT_POSITIVE_ACTION:
                mListener.onModelChange(IDiyMessage.PRODUCT_POSITIVE_ACTION_RESULT, loadProductPositive((Long) values[0]));
                break;
            case IDiyMessage.COMMENT_COUNT_ACTION:
                mListener.onModelChange(IDiyMessage.COMMENT_COUNT_ACTION_RESULT,loadCommentCount((Long) values[0]));
                break;
        }
    }

    private CommentCountBean loadCommentCount(Long value) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("productId", String.valueOf(value));
        String jsonStr = HttpUtils.getInstance().doPost(HttpConst.COMMENT_COUNT_URL, params);
        RResultBean rResultBean = JSON.parseObject(jsonStr, RResultBean.class);
        if (rResultBean.isSuccess()) {

            return JSON.parseObject(rResultBean.getResult(), CommentCountBean.class);
        }
        return new CommentCountBean();


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
}
