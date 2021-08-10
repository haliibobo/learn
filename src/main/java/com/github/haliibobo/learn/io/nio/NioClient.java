package com.github.haliibobo.learn.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NioClient {

    public static void main(String[] args) {// Socket 客户端（接收信息并打印）
        int port = 4344; //端口号
        // Socket 客户端（接收信息并打印）
        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println("NIO 客户端：" + s));
            List s = new ArrayList<>();
            Integer i = new Integer(11);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
