package com.jcourse.agolovenko.lesson3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * In used for Command and Calculator field to get or set information
 * about arguments of Math operation, current stack state
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface In {
    InjectionType value();
}
