package com.github.haliibobo.learn.net.http;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-09 20:41
 * @description describe what this class do
 */
public class HttpHandler implements Runnable {

    private int bufferSize = 1024;
    private String localCharset = "UTF-8";
    private SelectionKey key;

    public HttpHandler() {

    }

    public HttpHandler(SelectionKey key) {
        this.key = key;
    }

    public void handlerAccept() throws IOException {
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    public void handleRead() throws IOException {
        // 获取channel
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // 获取buffer并重置
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();

        // 没有读到内容则关闭
        if (socketChannel.read(buffer) == -1) {
            socketChannel.close();
        } else {
            // 接收请求数据
            buffer.flip();
            String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();

            // 控制台打印请求报文头
            String[] requestMessage = receivedString.split("\r\n");
            for (String s : requestMessage) {
                System.out.println(s);

                // 遇到空行说明报文头已经打印完
                if (StringUtils.isEmpty(s)) {
                    break;
                }
            }

            // 控制台打印首行信息
            String[] firstLine = requestMessage[0].split(" ");
            System.out.println();
            System.out.println("Method:\t" + firstLine[0]);
            System.out.println("url:\t" + firstLine[1]);
            System.out.println("HTTP Version:\t" + firstLine[2]);
            System.out.println();

            // 返回客户端
            StringBuilder sendString = new StringBuilder();
            sendString.append("HTTP/1.1 200 OK\r\n");// 响应报文首行,200表示处理成功
            sendString.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
            sendString.append("\r\n");// 报文头结束后加一个空行
            sendString.append("<html><head><title>显示报文</title></head></body>");
            sendString.append("接收到请求报文是:<br/>");
            for (String s : requestMessage) {
                sendString.append(s + "<br/>");
            }
            sendString.append("</body></html>");

            buffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
            socketChannel.write(buffer);
            socketChannel.close();
        }

    }

    @Override
    public void run() {
        try {
            // 接收到连接请求时
            if (key.isAcceptable()) {
                handlerAccept();
            }

            // 读数据
            if (key.isReadable()) {
                handleRead();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
