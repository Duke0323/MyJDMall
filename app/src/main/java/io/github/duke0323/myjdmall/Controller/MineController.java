package io.github.duke0323.myjdmall.Controller;

import android.content.Context;

import io.github.duke0323.myjdmall.config.IDiyMessage;
import io.github.duke0323.myjdmall.db.UserDao;

/**
 * Created by ${Duke} on 2016/7/12.
 */
public class MineController  extends BaseController{
    public MineController(Context context) {
        super(context);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action) {
            case IDiyMessage.DELETE_ACTION:
                mListener.onModelChange(IDiyMessage.DELETE_ACTION_RESULT, deleteUser());
                break;
        }
    }

    private boolean deleteUser() {
        UserDao dao = new UserDao(context);
        return dao.deleteUser();
    }
}
