package io.github.duke0323.myjdmall.utils;

/**
 * 账户管理
 * Created by ${Duke} on 2016/7/10.
 */
public class AccountMgr {
    public static Account get() {
        Account account = new Account();
        return account;
    }

    static class Account {
        boolean isLogin() {
            return false;
        }
    }
}
