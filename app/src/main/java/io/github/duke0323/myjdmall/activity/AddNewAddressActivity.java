package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RecAddressBean;
import io.github.duke0323.myjdmall.protocol.IAddressChangeListener;
import io.github.duke0323.myjdmall.ui.ProvinceSortPopWindow;

public class AddNewAddressActivity extends AppCompatActivity implements IAddressChangeListener {

    private android.widget.EditText nameet;
    private android.widget.EditText phoneet;
    private android.widget.TextView chooseprovincetv;
    private android.widget.EditText addressdetailset;
    private android.widget.CheckBox defaultcbx;
    private android.widget.LinearLayout parentview;
    private ProvinceSortPopWindow mProvinceSortPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        this.parentview = (LinearLayout) findViewById(R.id.parent_view);
        this.defaultcbx = (CheckBox) findViewById(R.id.default_cbx);
        this.addressdetailset = (EditText) findViewById(R.id.address_details_et);
        this.chooseprovincetv = (TextView) findViewById(R.id.choose_province_tv);
        this.phoneet = (EditText) findViewById(R.id.phone_et);
        this.nameet = (EditText) findViewById(R.id.name_et);
    }

    public void saveAddress(View view) {

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
        chooseprovincetv.setText(province.getName()+city.getName()+area.getName());
    }
}
