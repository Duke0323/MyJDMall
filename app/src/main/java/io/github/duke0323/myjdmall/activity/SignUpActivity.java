package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.duke0323.myjdmall.Controller.SignUpController;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.SignUpBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;

public class SignUpActivity extends BaseActivity implements IModelChangeListener {

    private android.widget.TextView titlev;
    private android.widget.EditText usernameet;
    private android.widget.EditText pwdet;
    private android.widget.EditText surepwdet;
    private SignUpController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.SIGNUP_ACTION_RESULT:
                    handleSignUpResult((SignUpBean) msg.obj);
                    break;
            }
        }
    };

    private void handleSignUpResult(SignUpBean obj) {
        if (obj.isSuccess()) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "注册失败" + obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        //下一步
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mController = new SignUpController(this);
        mController.setListener(this);
        this.surepwdet = (EditText) findViewById(R.id.surepwd_et);
        this.pwdet = (EditText) findViewById(R.id.pwd_et);
        this.usernameet = (EditText) findViewById(R.id.username_et);
        this.titlev = (TextView) findViewById(R.id.title_v);

    }

    public void signUpClick(View view) {
        String username = usernameet.getText().toString();
        String pwd = pwdet.getText().toString();
        String surepwd = surepwdet.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(surepwd)) {
            Toast.makeText(this, "必须全部填写", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(surepwd)) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        mController.sendAsyncMessage(IDiyMessage.SIGNUP_ACTION, username, pwd, surepwd);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        SignUpBean signUpBean = (SignUpBean) values[0];
        handler.obtainMessage(action, signUpBean).sendToTarget();
    }
}
