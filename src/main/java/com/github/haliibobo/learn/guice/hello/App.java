package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:41
 * @description describe what this class do
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new HelloModule());
        System.out.println("1.field inject");
        Client client = injector.getInstance(Client.class);
        client.say();
        System.out.println("2.constructor inject");
        ClientTwo clientTwo = injector.getInstance(ClientTwo.class);
        clientTwo.say();
        System.out.println("3.method inject");
        ClientThree clientThree = injector.getInstance(ClientThree.class);
        clientThree.say();

    }

}

