package io.github.duke0323.myjdmall.db;

/**
 * Created by ${Duke} on 2016/7/11.
 */
public class DbConst {
    public static final String DB_NAME = "jdmall.db";
    public static final int DB_VERSION = 1;
    public static final String USER_TABLE = "user";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_PASSWORD = "pwd";
    public static final String EXECUTE_SQL = "create table " + USER_TABLE
            + " (_id integer primary key autoincrement," + COLUMN_USERNAME
            + " varchar(20)," + COLUMN_PASSWORD + " varchar(40));";
}
