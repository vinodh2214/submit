package com.assesment.University.Exceptions;

public class CollegeAlreadyExistException extends RuntimeException{
    private String message;
    public CollegeAlreadyExistException(){}
    public CollegeAlreadyExistException(String msg){
        super(msg);
        this.message=msg;
    }
}
