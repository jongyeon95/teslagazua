package com.jongyeon.teslagazua.exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(){
        super("Email is not found");
    }
}


