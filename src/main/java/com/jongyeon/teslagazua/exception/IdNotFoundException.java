package com.jongyeon.teslagazua.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(){
        super("Id is not found");
    }
}
