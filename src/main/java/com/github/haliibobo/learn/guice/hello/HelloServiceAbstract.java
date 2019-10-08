package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Singleton;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:41
 * @description describe what this class do
 */
public abstract class HelloServiceAbstract implements HelloService {

    @Override
    public void sayHello(String word) {
          System.out.println(word);
    }

    public abstract void say (String word);
}
