package io.github.duke0323.myjdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.duke0323.myjdmall.Controller.MineController;
import io.github.duke0323.myjdmall.JDApplication;
import io.github.duke0323.myjdmall.R;
import io.github.duke0323.myjdmall.activity.LoginActivity;
import io.github.duke0323.myjdmall.bean.RLogin;
import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.protocol.IModelChangeListener;
import io.github.duke0323.myjdmall.utils.ActivityUtils;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class MIneFragment extends Fragment implements IModelChangeListener, View.OnClickListener {
    private CircleImageView mUserIconIv;
    private TextView mUserNameTv;
    private TextView mUserLevelTv;
    private LinearLayout mWaitPayLl;
    private TextView mWaitPayTv;
    private LinearLayout mWaitReceiveLl;
    private TextView mWaitReceiveTv;
    private LinearLayout mMimeOrder;
    private Button mLogoutBtn;
    private MineController mController;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            handleLogoutResult(msg);
        }
    };

    private void handleLogoutResult(Message msg) {
        if(msg.what==IDiyMessage.DELETE_ACTION_RESULT) {
            if((Boolean) msg.obj) {

                ActivityUtils.start(getActivity(), LoginActivity.class,true);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mController = new MineController(getContext());
        mController.setListener(this);
        assignViews();
        initUserInfo();
    }

    private void initUserInfo() {
        //获取全局变量
        RLogin userInfo = ((JDApplication) getActivity().getApplication()).mUserInfo;
        if(!TextUtils.isEmpty(userInfo.getUserName())) {
            mUserNameTv.setText(userInfo.getUserName());
        }
        switch (userInfo.getUserLevel()) {
            case 2:
                mUserLevelTv.setText("铜牌会员");
                break;
            case 3:
                mUserLevelTv.setText("银牌会员");
                break;
            case 4:
                mUserLevelTv.setText("金牌会员");
                break;
            case 5:
                mUserLevelTv.setText("钻石会员");
                break;
            case 1:
                mUserLevelTv.setText("注册会员");
                break;
        }
        mWaitPayTv.setText(String.valueOf(userInfo.getWaitPayCount()));
        mWaitReceiveTv.setText(String.valueOf(userInfo.getWaitReceiveCount()));
    }

    private void assignViews() {
        mUserIconIv = (CircleImageView) getActivity().findViewById(R.id.user_icon_iv);
        mUserNameTv = (TextView) getActivity().findViewById(R.id.user_name_tv);
        mUserLevelTv = (TextView) getActivity().findViewById(R.id.user_level_tv);
        mWaitPayLl = (LinearLayout) getActivity().findViewById(R.id.wait_pay_ll);
        mWaitPayTv = (TextView) getActivity().findViewById(R.id.wait_pay_tv);
        mWaitReceiveLl = (LinearLayout) getActivity().findViewById(R.id.wait_receive_ll);
        mWaitReceiveTv = (TextView) getActivity().findViewById(R.id.wait_receive_tv);
        mMimeOrder = (LinearLayout) getActivity().findViewById(R.id.mime_order);
        mLogoutBtn = (Button) getActivity().findViewById(R.id.logout_btn);
        mLogoutBtn.setOnClickListener(this);
    }

    @Override
    public void onModelChange(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                mController.sendAsyncMessage(IDiyMessage.DELETE_ACTION, 0);
                break;
        }
    }
}
