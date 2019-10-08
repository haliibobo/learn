package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:46
 * @description field inject
 */
public class Client {

    @Named("word")
    @Inject
    private String word;
    @Inject
    private HelloService helloService;

    public void say (){
        helloService.sayHello(word);
    }

}
