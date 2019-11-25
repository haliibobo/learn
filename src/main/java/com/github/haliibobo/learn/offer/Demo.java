package com.github.haliibobo.learn.offer;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Demo {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault(); //1、创建实例

        /* 访问我在香港机房的网站首页 */
        HttpGet httpGet=new HttpGet("");
        RequestConfig config=RequestConfig.custom()
            .setConnectTimeout(3000) // 设置连接超时时间 3秒钟
            .setSocketTimeout(10) // 设置读取超时时间0.01秒钟
            .build();
        httpGet.setConfig(config);

        CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet); //3、执行
        HttpEntity httpEntity=closeableHttpResponse.getEntity(); //4、获取实体

        System.out.println(EntityUtils.toString(httpEntity, "utf-8")); //获取网页内容

        closeableHttpResponse.close();
        closeableHttpClient.close();
    }
}
