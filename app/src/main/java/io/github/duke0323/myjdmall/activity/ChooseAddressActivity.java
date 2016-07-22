package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.adapter.AddressListAdapter;
import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IDeleteAddressChangeListener;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

/*
* bug
* 控制器中直接传入了false导致没有默认地址
* */
public class ChooseAddressActivity extends BaseActivity implements IModelChangeListener, IDeleteAddressChangeListener {
    private View noView;
    private android.widget.ListView lv;
    private AddressListAdapter mAdapter;
    private ShopCarController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.CHOOOSE_ADDRESS_ACTION_RESULT:
                    handleChooseAddress((List<AddressBean>) msg.obj);
                    break;
                case IDiyMessage.DELETE_ADDRESS_ACTION_RESULT:
                    handleDeleteAddress((RResultBean) msg.obj);
                    break;
            }
        }
    };

    private void handleDeleteAddress(RResultBean obj) {
        if (obj.isSuccess()) {
            mController.sendAsyncMessage(IDiyMessage.CHOOOSE_ADDRESS_ACTION, 0);
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "删除失败" + obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }


    }


    private void handleChooseAddress(List<AddressBean> obj) {
        if (obj.size() != 0) {
            mAdapter.setDatas(obj);
            mAdapter.notifyDataSetChanged();
            mAdapter.setListener(this);
            lv.setVisibility(View.VISIBLE);
            noView.setVisibility(View.INVISIBLE);
        } else {
            lv.setVisibility(View.INVISIBLE);
            noView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        initController();
        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddressBean addressBean = (AddressBean) mAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra(IntentValues.CHOOSEADDRESS,addressBean);
                setResult(SettleActivity.CHOOSEADDRESS,intent);
                finish();

            }
        });
        noView = findViewById(R.id.null_view);
        mAdapter = new AddressListAdapter(this);
        lv.setAdapter(mAdapter);
        mController.sendAsyncMessage(IDiyMessage.CHOOOSE_ADDRESS_ACTION, 0);
    }

    public void initController() {
        mController = new ShopCarController(this);
        mController.setListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }


    @Override
    public void onDelete(long id) {
        mController.sendAsyncMessage(IDiyMessage.DELETE_ADDRESS_ACTION, id);

    }
}
