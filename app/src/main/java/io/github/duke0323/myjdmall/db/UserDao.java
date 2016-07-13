package io.github.duke0323.myjdmall.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import io.github.duke0323.myjdmall.bean.UserBean;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class UserDao {

    private final DbHelper mDbHelper;

    public UserDao(Context context) {
        mDbHelper = new DbHelper(context);
    }


    //保存用户信息
    public void saveUser(String name, String pwd) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConst.COLUMN_USERNAME, name);
        values.put(DbConst.COLUMN_PASSWORD, pwd);
        db.insert(DbConst.USER_TABLE, null, values);
        db.close();
    }

    //查询当前登陆信息
    public UserBean queryLogin() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = mDbHelper.getReadableDatabase();
            cursor = db.query(DbConst.USER_TABLE, new String[]{DbConst.COLUMN_USERNAME, DbConst.COLUMN_PASSWORD},
                    null, null, null, null, null);
            if (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(DbConst.COLUMN_USERNAME));
                String pwd = cursor.getString(cursor.getColumnIndex(DbConst.COLUMN_PASSWORD));
                return new UserBean(name, pwd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return null;
    }

    //删除用户的信息
    public boolean deleteUser() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int deleteCount = db.delete(DbConst.USER_TABLE, null, null);
        db.close();
        return deleteCount > 0;
    }
}
