package io.github.iromul.playground.jcip.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface GuardedBy {

    String value() default "";
}
