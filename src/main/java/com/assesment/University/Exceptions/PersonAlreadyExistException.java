package com.assesment.University.Exceptions;

public class PersonAlreadyExistException extends RuntimeException{
    private String message;
    public PersonAlreadyExistException(){}
    public PersonAlreadyExistException(String msg){
        super(msg);
        this.message=msg;
    }
}
