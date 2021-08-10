package com.github.haliibobo.learn.io.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketService {
    // Socket 服务器端（简单的发送信息）
    public static void main(String[] args) throws Exception{
        int port = 4343; //端口号
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 等待连接
            Socket socket = serverSocket.accept();
            Thread sHandlerThread = new Thread(() -> {
                try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                    printWriter.println("hello world！:" + new Date());
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            sHandlerThread.start();
        }
    }
}
