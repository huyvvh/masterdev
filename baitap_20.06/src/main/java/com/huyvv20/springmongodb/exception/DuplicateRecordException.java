package com.huyvv20.springmongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String sms){
        super(sms);
    }
}
