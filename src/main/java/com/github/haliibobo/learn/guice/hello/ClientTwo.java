package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:46
 * @description constructor inject
 */
public class ClientTwo {
    private String word;
    private HelloService helloService;

    @Inject
    public ClientTwo(@Named("word") String word,HelloService helloService){
        this.word = word;
        this.helloService = helloService;
    }

    public void say (){
        helloService.sayHello(word);
    }

}
