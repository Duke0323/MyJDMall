package io.github.duke0323.myjdmall;

import android.app.Application;

import com.orhanobut.logger.Logger;

import io.github.duke0323.myjdmall.bean.RLogin;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class JDApplication extends Application {
    //全局成员变量
    /*http://blog.csdn.net/qq_23547831/article/details/51655330
    * 与大家普遍的看法不同之处在于，当进程被干掉之后，
    * 实际上app不会重新开始启动。
    * Android系统会创建一个新的Application 对象，
    * 然后启动上次用户离开时的activity以造成这个app从来没有被kill掉得假象。
    * 而这时候静态变量等数据由于进程已经被杀死而被初始化，
    * 所以就有了我们的不推荐在静态变量（包括Application中保存全局数据静态数据）的观点。*/
    public RLogin mUserInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("duke");
    }
}
