package com.github.haliibobo.learn.finagle;

import com.twitter.finagle.Service;
import com.twitter.finagle.SimpleFilter;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.util.Duration;
import com.twitter.util.Function;
import com.twitter.util.Future;
import com.twitter.util.Stopwatch$;
import scala.Function0;

public final class LoggingFilter extends SimpleFilter<Request, Response> {

    @Override
    public Future<Response> apply(Request req, Service<Request, Response> service) {
        final Function0<Duration> start = Stopwatch$.MODULE$.start();

        return service.apply(req).map(new Function<Response, Response>() {
            @Override
            public Response apply(Response resp) {
                Duration elapsed = start.apply();
                System.out.println("Took: " + elapsed.inMilliseconds() + "ms to complete.");

                return resp;
            }
        });
    }
}
