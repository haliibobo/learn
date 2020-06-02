package com.github.haliibobo.learn.file;

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
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "learn.conf");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder sb  = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb);
    }

}
