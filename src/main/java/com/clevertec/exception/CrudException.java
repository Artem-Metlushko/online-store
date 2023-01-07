package com.clevertec.exception;

public class CrudException extends RuntimeException{
    public CrudException(Throwable throwable) {
        super(throwable);
    }
}
