package com.assesment.University.Exceptions;

public class NoSuchPersonExistsException extends RuntimeException{
    private String message;
    public NoSuchPersonExistsException(Long id){}
    public NoSuchPersonExistsException(String msg){
        super(msg);
        this.message=msg;
    }
}
