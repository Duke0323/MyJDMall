package io.github.duke0323.myjdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import io.github.duke0323.myjdmall.Controller.ShopCarController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.AddAddressParams;
import io.github.duke0323.myjdmall.bean.AddressBean;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.RecAddressBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.config.IntentValues;
import io.github.duke0323.myjdmall.protocol.IAddressChangeListener;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.ui.ProvinceSortPopWindow;

public class AddNewAddressActivity extends BaseActivity implements IAddressChangeListener, IModelChangeListener {

    private android.widget.EditText nameet;
    private android.widget.EditText phoneet;
    private android.widget.TextView chooseprovincetv;
    private android.widget.EditText addressdetailset;
    private android.widget.CheckBox defaultcbx;
    private android.widget.LinearLayout parentview;
    private ProvinceSortPopWindow mProvinceSortPopWindow;
    private RecAddressBean mProvince;
    private RecAddressBean mCity;
    private RecAddressBean mArea;
    private ShopCarController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.ADD_ADDRESS_ACTION_RESULT:
                    handleAddresss((RResultBean) msg.obj);
                    break;
            }
        }
    };

    private void handleAddresss(RResultBean obj) {
        if (obj.isSuccess()) {
            AddressBean addressBean = JSON.parseObject(obj.getResult(), AddressBean.class);
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra(IntentValues.ADDADDRESS,addressBean);
            setResult(SettleActivity.ADDADDRESS,intent);
            finish();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        initController();
        this.parentview = (LinearLayout) findViewById(R.id.parent_view);
        this.defaultcbx = (CheckBox) findViewById(R.id.default_cbx);
        this.addressdetailset = (EditText) findViewById(R.id.address_details_et);
        this.chooseprovincetv = (TextView) findViewById(R.id.choose_province_tv);
        this.phoneet = (EditText) findViewById(R.id.phone_et);
        this.nameet = (EditText) findViewById(R.id.name_et);
    }

    public void initController() {
        mController = new ShopCarController(this);
        mController.setListener(this);
    }

    public void saveAddress(View view) {
        String name = nameet.getText().toString().trim();
        String phone = phoneet.getText().toString().trim();
        String addressDetails = addressdetailset.getText().toString().trim();
        String chooseProvince = chooseprovincetv.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(addressDetails)
                || TextUtils.isEmpty(chooseProvince) || mProvince == null
                || mCity == null || mArea == null) {
            Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
            return;
        }
        String mProvinceCode = mProvince.getCode();
        String mCityCode = mCity.getCode();
        String mAreaCode = mArea.getCode();
        boolean checked = defaultcbx.isChecked();
        AddAddressParams addAddressParams
                = new AddAddressParams(name, phone, mProvinceCode, mCityCode, mAreaCode, addressDetails, checked);
        mController.sendAsyncMessage(IDiyMessage.ADD_ADDRESS_ACTION, addAddressParams);


    }

    public void reGetAddress(View view) {
        if (mProvinceSortPopWindow == null) {
            mProvinceSortPopWindow = new ProvinceSortPopWindow(this);
            mProvinceSortPopWindow.setListener(this);
        }
        mProvinceSortPopWindow.onShow(parentview);
    }


    @Override
    public void onChange(RecAddressBean province, RecAddressBean city, RecAddressBean area) {
        chooseprovincetv.setText(province.getName() + city.getName() + area.getName());
        mProvince = province;
        mCity = city;
        mArea = area;
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }
}
