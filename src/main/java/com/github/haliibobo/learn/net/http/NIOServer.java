package com.github.haliibobo.learn.net.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-09 20:35
 * @description describe what this class do
 */
public class NIOServer{
    public static void main(String[] args) throws IOException {
    // 创建ServerSocketChannel,监听8080端口
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(8080));

    // 设置为非阻塞模式
    serverSocketChannel.configureBlocking(false);

    // 为serverSocketChannel注册选择器
    Selector selector = Selector.open();
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    // 创建处理器
    while (true) {
        // 等待请求,每次等待阻塞3s,超过3s后线程继续向下运行,如果传入0或者不传入参数将一直阻塞
        if (selector.select(3000) == 0) {
            System.out.println("等待请求超时....");
            continue;
        }
        System.out.println("处理请求....");

        // 获取待处理的SelectionKey
        Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
        while (keyIter.hasNext()) {
            SelectionKey key = keyIter.next();
            // 启动新线程处理SelectionKey
            new Thread(new HttpHandler(key)).run();
            // 处理完毕,从待处理的SelectionKey迭代器中移除当前所使用的key
            keyIter.remove();
        }
    }
}
}

