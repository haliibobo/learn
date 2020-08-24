package com.github.haliibobo.learn.flink.http;

import com.github.haliibobo.learn.java.http.OkhttpUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import okhttp3.Callback;

public class HttpFetcher {
    private static   Config config;

    static {
       config = ConfigFactory.load("geo.conf").resolve();
    }

    public static void fetcher(Callback callback) {
        //String weather = "http://wttr.in/Beijing";

        config.getStringList("china").forEach(area->
            OkhttpUtil.getInstance().getAsync("http://flash.weather.com.cn/wmaps/xml/"+area+".xml",callback)
        );
    }
}
