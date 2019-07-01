package com.xlgcx.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by huyg on 2018/7/12.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface TypeString {
}
