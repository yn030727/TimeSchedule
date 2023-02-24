package com.example.module_login.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        //创建一个OkHttpClient实例，创建一个request对象，调用newcall方法创建一个call对象，再调用call的enqueue执行异步请求，
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
