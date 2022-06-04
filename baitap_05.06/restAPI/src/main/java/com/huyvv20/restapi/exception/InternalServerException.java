package com.huyvv20.restapi.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String sms){
        System.out.print(sms);
    }
}
