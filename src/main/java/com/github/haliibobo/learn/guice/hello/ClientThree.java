package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Inject;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:46
 * @description method inject
 */
public class ClientThree {

    @Inject
    public void setHello(Hello hello) {
        this.hello = hello;
    }

    private Hello hello;

    public void say (){
        hello.sayHello();
    }

}
