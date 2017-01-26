package com.home.mvc.annotation;

/**
 * Created by liyang on 30/12/2016.
 * li.acerphoenix@gmail.com
 * email to me maybe
 */
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Nullable {
}

