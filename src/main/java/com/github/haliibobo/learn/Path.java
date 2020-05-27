package com.github.haliibobo.learn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-05-08 16:43
 * @description describe what this class do
 */
public class Path {
    public static void main(String[] args){
        getClassesWithAnnotationFromPackage("com.github.haliibobo.learn.redis");
    }
    private static void getClassesWithAnnotationFromPackage(String packageName) {
        String packageDirName = packageName.replace('.', '/');
        Enumeration dirs = null;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        } catch (IOException var12) {
            return;
        }

        while(dirs.hasMoreElements()) {
            URL url = (URL)dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = null;
                try {
                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    System.out.println(filePath);
                } catch (UnsupportedEncodingException var10) {
                }
                filePath = filePath.substring(1);
                System.out.println(filePath);
            }
        }
        return;
    }
}
