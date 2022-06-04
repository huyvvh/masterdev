package com.huyvv20.restapi.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String sms){
        System.out.print(sms);
    }
}
