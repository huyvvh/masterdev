package com.huyvv20.restapi.exception;

public class DuplicateRecException extends RuntimeException{
    public DuplicateRecException(String sms){
        System.out.print(sms);
    }
}
