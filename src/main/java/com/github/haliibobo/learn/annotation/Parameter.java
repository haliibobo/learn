package com.github.haliibobo.learn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface Parameter {

    /**
     * 使用指定的 name.
     */
    String nameOverride() default "";

    /**
     * 使用类名作为前缀.
     */
    boolean simpleClassNamePrefix() default true;
}
