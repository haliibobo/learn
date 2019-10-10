package com.github.haliibobo.learn.java.lam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

/**
 * @Auther: lizibo
 * @Date: 2018/7/4 20:57
 * @Description:
 */
public class CommandTest {

    private static final Random random = new Random();
    public static void main(String args[]){
       // long l =93184901062;
        long l =93184901062L;
        char a ='▓';

        Double d =((double)l)/1024/1024;
       System.out.println(d);
       System.out.println(String.valueOf(null));
    }

    public static String getHdfsFilePath(String path, String file) {
        return path.charAt(path.length() - 1) == '/' ? path + file : path + "/" + file;
    }

    public static  String getFullReadyPath(String readyFile){
        return readyFile.substring(0,readyFile.lastIndexOf("/")+1);
    }



    public static void sql() {

        int[] a ={1,2};
        try{
            System.out.println(a[3]);
            DriverManager.setLoginTimeout(1);
            //Class.forName(driverClass).newInstance();
            Connection conn = DriverManager.getConnection("","","");
        }catch (SQLException e){

        }

       System.out.println(2222);
    }
}
