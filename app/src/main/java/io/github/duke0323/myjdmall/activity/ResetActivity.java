package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.duke0323.myjdmall.Controller.ResetController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

public class ResetActivity extends BaseActivity implements IModelChangeListener {

    private android.widget.TextView titlev;
    private android.widget.EditText usernameet;
    private ResetController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.RESET_ACTION_RESULT:
                    handleResetResult((RResultBean) msg.obj);
                    break;
            }
        }
    };

    private void handleResetResult(RResultBean obj) {
        if(obj.isSuccess()) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "失败" + obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        this.usernameet = (EditText) findViewById(R.id.username_et);
        this.titlev = (TextView) findViewById(R.id.title_v);
        mController = new ResetController(this);
        mController.setListener(this);
    }

    public void resetClick(View view) {
        String username = usernameet.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mController.sendAsyncMessage(IDiyMessage.RESET_ACTION, username);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        RResultBean rResultBean = (RResultBean) values[0];
        handler.obtainMessage(action, rResultBean).sendToTarget();
    }
}
