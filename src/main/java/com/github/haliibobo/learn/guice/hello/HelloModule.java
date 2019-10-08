package com.github.haliibobo.learn.guice.hello;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:43
 * @description describe what this class do
 */
public class HelloModule extends AbstractModule {

    @Override
    public void configure() {
        bind(String.class).annotatedWith(Names.named("word")).toInstance("hello");
        bind(String.class).annotatedWith(Names.named("say")).toInstance("say");
        bind(String.class).annotatedWith(Names.named("who")).toInstance("halibobo");
        bind(HelloService.class).to(HelloServiceAbstract.class);
        bind(HelloServiceAbstract.class).to(HelloServiceImplTwo.class);
    }

}
