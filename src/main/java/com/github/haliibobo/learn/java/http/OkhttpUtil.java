package com.github.haliibobo.learn.java.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OkhttpUtil {
    private OkHttpClient client;

    private static volatile  OkhttpUtil okhttpUtil;

    private OkhttpUtil (){
        this.client = new OkHttpClient();
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

    public void test() {
        Request request = new Request.Builder().url("https://github.com/vuejs/vue-cli")
                .get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println(response.body().string());

            }
        });
    }

    public void test2 () {
         MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        JSONObject feedback = new JSONObject();
        feedback.put("rtm" ,System.currentTimeMillis()+"");
        feedback.put("status","false");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",feedback.toString());
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toJSONString());
        String url = "http://rsm.jd.com/api/feedback/rec/recfeeder/clk@behavior@th";
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
