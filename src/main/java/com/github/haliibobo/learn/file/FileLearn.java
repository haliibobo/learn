package com.github.haliibobo.learn.file;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-02 16:26
 * @description describe what this class do
 */
public class FileLearn {


    @Test
    public void test() throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("learn.conf");
        Config config = ConfigFactory.parseReader(new InputStreamReader(in)).resolve();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "learn.conf");
        Config learn = ConfigFactory.load("learn.conf");
    }

}
