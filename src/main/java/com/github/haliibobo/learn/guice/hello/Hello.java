package com.github.haliibobo.learn.guice.hello;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * sayHello something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-09-10 15:09
 * @description inject type: field、method
 */
@Singleton
public class Hello {
    @Named("word")
    @Inject
    private String word;

    private String say;

    private String who;

    @Inject
    public Hello(@Named("who") String who) {
        this.who = who;
    }

    @Inject
    public void setSay( @Named("say") String say) {
        this.say = say;
    }

    public void sayHello(){
        System.out.println(who + "," + say +"," + word);
    }

}
