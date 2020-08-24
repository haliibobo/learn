package com.github.haliibobo.learn.java.http;

import com.github.haliibobo.learn.flink.kafka.pojo.Weather;
import com.github.haliibobo.learn.tools.XmlUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Okhttp {

    private static final Map<String, List<Weather>> info =  new ConcurrentHashMap<>();
    private static  CountDownLatch c;

    static {
        Config config = ConfigFactory.load("geo.conf");
        c = new CountDownLatch(config.getStringList("china").size());
    }

    public static void main(String[] args) throws InterruptedException {
        //String weather = "http://wttr.in/Beijing";

        Config config = ConfigFactory.load("geo.conf").resolve();
        final Callback callback = new XmlCallback();
        config.getStringList("china").forEach(area->
            OkhttpUtil.getInstance().getAsync("http://flash.weather.com.cn/wmaps/xml/"+area+".xml",callback)
        );
        c.await();
        info.forEach((k,v) ->{
            System.out.println(k + " = [");
            v.forEach(System.out::println);
            System.out.println("]");
            System.out.println();
        });
    }

    private static class XmlCallback implements Callback{

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {

        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            assert response.body() != null;
            String xml = response.body().string();
            try {
                Element root = XmlUtil.getInstance().getRoot(xml);
                List<Weather> v = XmlUtil.getInstance().xml2List(root).stream().map( m -> {
                    try {
                        return XmlUtil.getInstance().mapToBean(m, Weather.class);
                    } catch (Exception ignored) { }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
                info.put(root.getName(),v);
                c.countDown();
            } catch (DocumentException e) {
                System.out.println(call.request().url());
                c.countDown();
            }
        }
    }
}
