package com.home.mvc.exception;

/**
 * Created by liyang on 26/1/2017.
 * li.acerphoenix@gmail.com;
 * email to me
 * better exception overwrite fillintrace to get better performance.
 */
public class TunningException extends Exception{

    public TunningException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }
}
