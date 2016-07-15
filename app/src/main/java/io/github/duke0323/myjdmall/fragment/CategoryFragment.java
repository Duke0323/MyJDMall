package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.CategoryController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.TopCategoryAdapter;
import io.github.duke0323.myjdmall.bean.CategoryBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.SubCategoryView;

/**
 * 分类页面
 * Created by ${Duke} on 2016/7/11.
 */
public class CategoryFragment extends BaseFragemnt implements IModelChangeListener, AdapterView.OnItemClickListener {
    private EditText mSearchEt;
    private ListView mTopLv;
    private TopCategoryAdapter mTopCategoryAdapter;
    private CategoryController mController;
    private SubCategoryView subcategory;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.CATEGORY_TOP_ACTION_RESULT:
                    handleCategory((List<CategoryBean>) msg.obj);
                    break;

            }
        }
    };

    private void handleCategory(List<CategoryBean> obj) {
        if (obj.size() > 0) {
            mTopCategoryAdapter.setDatas(obj);
            mTopCategoryAdapter.notifyDataSetChanged();
            mTopLv.performItemClick(null,0,0);


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        assignViews();
        initController();
        mTopCategoryAdapter = new TopCategoryAdapter(getContext());
        mTopLv.setAdapter(mTopCategoryAdapter);
        mTopLv.setOnItemClickListener(this);

    }

    private void initController() {
        mController = new CategoryController(getContext());
        mController.setListener(this);
        mController.sendAsyncMessage(IDiyMessage.CATEGORY_TOP_ACTION, 0);

    }


    private void assignViews() {
        mSearchEt = (EditText) getActivity().findViewById(R.id.search_et);
        mTopLv = (ListView) getActivity().findViewById(R.id.top_lv);
        subcategory = (SubCategoryView) getActivity().findViewById(R.id.subcategory);
    }


    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //将当前位置告诉adapter
        mTopCategoryAdapter.mTabPosition = position;
        mTopCategoryAdapter.notifyDataSetChanged();
        CategoryBean bean = (CategoryBean) mTopCategoryAdapter.getItem(position);
        subcategory.onShow(bean);





    }
}
