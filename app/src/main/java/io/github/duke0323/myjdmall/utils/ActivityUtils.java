package io.github.duke0323.myjdmall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ${Duke} on 2016/7/10.
 */
public class ActivityUtils {
    public static void start(Context context,Class clazz,boolean isFinish) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        if(isFinish) {
            ((Activity)context).finish();
        }
    }
}
