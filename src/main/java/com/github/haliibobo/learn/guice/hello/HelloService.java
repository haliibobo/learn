package com.github.haliibobo.learn.guice.hello;

import com.google.inject.ImplementedBy;
import com.google.inject.Singleton;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 14:39
 * @description describe what this class do
 */
//@ImplementedBy(HelloServiceImpl.class)
public interface HelloService {

    public void sayHello (String word);

}
