package com.github.haliibobo.learn.java.config;

import com.github.haliibobo.learn.java.collection.GsonUtil;
import com.google.gson.JsonElement;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-11 18:00
 * @description describe what this class do
 */
public class Test {

    public static void main(String[] args) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("learn.conf");
        Config config = ConfigFactory.parseReader(new InputStreamReader(in)).resolve();

        String s = config.resolve().root().render(ConfigRenderOptions.concise());
        System.out.println(s);
        JsonElement jsonElement = GsonUtil.fromJson(s, JsonElement.class);
        System.out.println(jsonElement);
    }

}
