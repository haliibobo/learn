package com.github.haliibobo.learn.finagle;

import com.twitter.finagle.Http;
import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Service;
import com.twitter.finagle.http.HttpMuxer;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.util.Await;
import com.twitter.util.Duration;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-19 11:31
 * @description describe what this class do
 */
public class HttpServer {

    private HttpServer() {

    }

    /**
     * Start the server.
     */
    public static void main(String[] args) throws Exception {



        Config config = ConfigFactory.load("finagle.conf");

        int maxConcurrentRequests = config.getInt("server.concurrentSize");
        int maxWaiters = maxConcurrentRequests;
        Duration processTimeoutD = Duration.fromMilliseconds(
            config.getDuration("server.processTimeout",TimeUnit.MILLISECONDS));
        int port = config.getInt("server.port");

        System.out.println("port:" + port);

        ServerSocket s = new ServerSocket();
         s.bind(new InetSocketAddress(
            InetAddress.getLoopbackAddress(), port));

        LoggingFilter accessLog = new LoggingFilter();
        NullToNotFound nullFilter = new NullToNotFound();
        HandleErrors errorsFilter = new HandleErrors();
        Service<Request, Response> service = accessLog
            .andThen(nullFilter)
            .andThen(errorsFilter)
            .andThen(router());

        ListeningServer server = Http.server()
            .withCompressionLevel(2)
            .withAdmissionControl().concurrencyLimit(maxConcurrentRequests, maxWaiters)
            .withRequestTimeout(processTimeoutD)
            .withSession().maxLifeTime(processTimeoutD)
            .serve(new InetSocketAddress(InetAddress.getLoopbackAddress(),port), service);

        Await.ready(server);

    }

    private static HttpMuxer router() {
        return new HttpMuxer()
            .withHandler("/cat", Handlers.echoHandler());
    }
}
