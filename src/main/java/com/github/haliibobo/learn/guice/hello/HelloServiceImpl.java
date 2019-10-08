package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Singleton;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-11 16:58
 * @description describe what this class do
 */
@Singleton
public class HelloServiceImpl extends HelloServiceAbstract {

    @Override
    public void say(String word) {
        System.out.println("<<<<<>>>>>");
            sayHello(word);
    }

    @Override
    public void sayHello(String word) {
        System.out.println("One," + word);
    }
}
