package com.example.timeschedule.application;

public class AccountInformation {
    private String account = null;
    private String user = null;
    private boolean isLogin = false;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setIsLogin(boolean idLogin) {
        this.isLogin = idLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
