package io.github.duke0323.myjdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import io.github.duke0323.myjdmall.Controller.LoginController;
import io.github.duke0323.myjdmall.JDApplication;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.bean.RResultBean;
import io.github.duke0323.myjdmall.bean.RLogin;
import io.github.duke0323.myjdmall.bean.UserBean;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.db.UserDao;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.utils.AESUtils;
import io.github.duke0323.myjdmall.utils.ActivityUtils;

public class LoginActivity extends BaseActivity implements IModelChangeListener {

    private android.widget.TextView titlev;
    private android.widget.EditText nameet;
    private android.widget.EditText pwdet;
    private LoginController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.LOGIN_ACTION_RESULT:
                    handleLoginResult((RResultBean) msg.obj);
                    break;
                case IDiyMessage.SAVE_ACTION_RESULT:
                    handleLodingLoginSave(msg.obj);
                    break;
            }
        }
    };

    private void handleLodingLoginSave(Object obj) {
        if (obj != null && obj instanceof UserBean) {
            try {
                UserBean bean = (UserBean) obj;
                nameet.setText(bean.getName());
                pwdet.setText(AESUtils.decrypt(bean.getPwd()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLoginResult(RResultBean obj) {
        if (!obj.isSuccess()) {
            Toast.makeText(this, obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        //成功登陆
        RLogin userInfo = JSON.parseObject(obj.getResult(), RLogin.class);
        //保存到数据库
        saveNameAndPwd();
        //保存到全局变量
        ((JDApplication) getApplication()).mUserInfo = userInfo;
        ActivityUtils.start(LoginActivity.this, MainActivity.class, true);
    }

    private void saveNameAndPwd() {
        new Thread() {
            public void run() {
                try {
                    String name = nameet.getText().toString().trim();
                    String pwd = pwdet.getText().toString().trim();
                    pwd = AESUtils.encrypt(pwd);
                    UserDao dao = new UserDao(LoginActivity.this);
                    dao.saveUser(name, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.pwdet = (EditText) findViewById(R.id.pwd_et);
        this.nameet = (EditText) findViewById(R.id.name_et);
        this.titlev = (TextView) findViewById(R.id.title_v);
        mController = new LoginController(LoginActivity.this);
        mController.setListener(this);
        mController.sendAsyncMessage(IDiyMessage.SAVE_ACTION, 0);

    }

    public void resetPwdClick(View view) {
        ActivityUtils.start(LoginActivity.this, ResetActivity.class, false);


    }

    public void registClick(View view) {
        ActivityUtils.start(LoginActivity.this, SignUpActivity.class, false);
    }

    public void loginClick(View view) {
        String name = nameet.getText().toString();
        String pwd = pwdet.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //网络请求
        mController.sendAsyncMessage(IDiyMessage.LOGIN_ACTION, name, pwd);
        ActivityUtils.start(LoginActivity.this, MainActivity.class, true);
    }


    @Override
    public void onModelChange(int action, Object... values) {
        //RResultBean loginResultBean = (RResultBean) values[0];
        handler.obtainMessage(action, values[0]).sendToTarget();
    }
}
