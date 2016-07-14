package io.github.duke0323.myjdmall.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.CategoryController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.ProductListActivity;
import io.github.duke0323.myjdmall.bean.CategoryBean;
import io.github.duke0323.myjdmall.bean.SubCategoryBean;
import io.github.duke0323.myjdmall.config.HttpConst;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.protocol.IViewContainer;

/**
 * Created by ${Duke} on 2016/7/13.
 */
public class SubCategoryView extends ScrollView implements IViewContainer, IModelChangeListener {
    private LinearLayout child_container_ll;
    private CategoryBean mBean;
    private CategoryController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.CATEGORY_SUB_ACTION_RESULT:
                    handleLoadSubCategoty((List<SubCategoryBean>) msg.obj);
                    break;
            }
        }
    };

    private void handleLoadSubCategoty(List<SubCategoryBean> obj) {
        child_container_ll.removeAllViews();
        if (obj.size() > 0) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                            , 110));
            Picasso.with(getContext()).load(HttpConst.DOMAIN + mBean.getBannerUrl())
                    .config(Bitmap.Config.RGB_565).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            child_container_ll.addView(imageView);

            for (int i = 0; i < obj.size(); i++) {
                TextView textView = new TextView(getContext());
                SubCategoryBean subCategoryBean = obj.get(i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 20, 0, 10);
                textView.setLayoutParams(params);
                textView.setText(subCategoryBean.getName());
                child_container_ll.addView(textView);
                List<CategoryBean> thirdCategory = subCategoryBean.getThirdCategory();

                //计算行数
                int lines = thirdCategory.size() / 3;
                lines = thirdCategory.size() % 3 == 0 ? lines : lines + 1;
                for (int j = 0; j < lines; j++) {
                    LinearLayout thirdLL = new LinearLayout(getContext());
                    LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                            , ViewGroup.LayoutParams.WRAP_CONTENT);
                    llParams.setMargins(0, 10, 0, 0);
                    thirdLL.setLayoutParams(llParams);

                    child_container_ll.addView(thirdLL);
                    //第一列
                    addColumns(thirdCategory, 3 * j, thirdLL);
                    //第二列
                    if (3 * j + 1 <= thirdCategory.size() - 1) {
                        addColumns(thirdCategory, 3 * j + 1, thirdLL);
                    }
                    //第三列
                    if (3 * j + 2 <= thirdCategory.size() - 1) {
                        addColumns(thirdCategory, 3 * j + 2, thirdLL);
                    }


                }
            }


        }
    }

    public void addColumns(List<CategoryBean> beans, int index, LinearLayout thirdLL) {
        final CategoryBean categoryBean = beans.get(index);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳入到商品详情
                int categoryid = categoryBean.getId();
                Intent intent = new Intent(getContext(),ProductListActivity.class);
                intent.putExtra(IntentValues.CATEGORYID,categoryid);
                ((Activity)getContext()).startActivity(intent);

            }
        });
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                getWidth()/3
                , getWidth()/3);
        params.setMargins(0,8,0,0);
        linearLayout.setLayoutParams(params);
        ImageView imageView = new ImageView(getContext());
        Picasso.with(getContext()).load(HttpConst.DOMAIN+categoryBean.getBannerUrl())
                .config(Bitmap.Config.RGB_565).into(imageView);
        linearLayout.addView(imageView);
        TextView textView = new TextView(getContext());
        textView.setText(categoryBean.getName());
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);
        thirdLL.addView(linearLayout);
    }

    public SubCategoryView(Context context) {
        super(context);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initController();
        initView();
    }

    private void initController() {
        mController = new CategoryController(getContext());
        mController.setListener(this);
    }

    private void initView() {
        child_container_ll = (LinearLayout) findViewById(R.id.child_container_ll);

    }

    @Override
    public void onShow(Object... values) {
        mBean = (CategoryBean) values[0];
        mController.sendAsyncMessage(IDiyMessage.CATEGORY_SUB_ACTION, mBean);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }
}
