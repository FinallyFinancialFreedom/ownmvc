package com.home.mvc.util;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by liyang on 4/1/2017.
 * li.acerphoenix@gmail.com
 * email to me maybe
 */
@Data
public class ReflectCache {
    private Field[] fields;
    private Map<Field,Method> methods;
}
