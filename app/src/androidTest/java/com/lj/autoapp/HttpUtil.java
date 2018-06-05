package com.lj.autoapp;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lulingjie on 2018/5/30.
 */

public class HttpUtil {
    private static final String GET_URL = "http://api.imbiker.cn/wx?p=2";
    private static final String POST_URL = "http://api.imbiker.cn/wx/";

    public static String get() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(GET_URL).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String post(Map<String, String> body) throws IOException{
        RequestBody requestBody = new FormBody.Builder()
                .add("id", body.get("id"))
                .add("status", body.get("status"))
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(POST_URL)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
