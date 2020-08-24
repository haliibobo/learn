package com.github.haliibobo.learn.java.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkhttpUtil {
    private OkHttpClient client;

    private static volatile  OkhttpUtil okhttpUtil;

    private OkhttpUtil (){
        this.client = new OkHttpClient().newBuilder()
                .connectionPool(new ConnectionPool()).build();
    }

     public static OkhttpUtil getInstance() {
        if (okhttpUtil == null){
            synchronized (OkhttpUtil.class){
                if (okhttpUtil == null){
                    okhttpUtil = new OkhttpUtil();
                }
            }
    }
        return okhttpUtil;
    }

    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url)
                .get().build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public Response getSync(String url ) throws IOException {
        Request request = new Request.Builder().url(url)
                .get().build();
        Call call = client.newCall(request);
        return  call.execute();
    }

    public void postAsync (String url ) {
         MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        JSONObject feedback = new JSONObject();
        feedback.put("rtm" ,System.currentTimeMillis()+"");
        feedback.put("status","false");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",feedback.toString());
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toJSONString());
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    System.out.println(jsonObject.toJSONString());
                    e.printStackTrace();
                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response)
                    throws IOException {
                    assert response.code() == 200;
                    System.out.println(jsonObject.toJSONString());
                    System.out.println(response);
                }
            });


    }


}
