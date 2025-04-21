package com.example.demo.exception;

public class PaytmServerDownException extends RuntimeException{
    public PaytmServerDownException(String message){
        super(message);
    }
}
