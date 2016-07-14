package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import io.github.duke0323.myjdmall.bean.CategoryBean;
import io.github.duke0323.myjdmall.bean.LoginResultBean;
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
                mListener.onModelChange(IDiyMessage.CATEGORY_SUB_ACTION_RESULT,loadSub(bean.getId()));
                break;
        }
    }

    private List<CategoryBean> loadTop(int parentId) {
        String url = HttpConst.CATEGORY_URL;
        if (parentId != 0) {
            url = url + "?parentId=" + parentId;
        }
        String jsonStr = HttpUtils.doGet(url);

        LoginResultBean resultBean = JSON.parseObject(jsonStr, LoginResultBean.class);
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
        String jsonStr = HttpUtils.doGet(url);

        LoginResultBean resultBean = JSON.parseObject(jsonStr, LoginResultBean.class);
        if (resultBean.isSuccess()) {
            String result = resultBean.getResult();
            return JSON.parseArray(result, SubCategoryBean.class);
        }
        return new ArrayList<SubCategoryBean>();
    }
}
