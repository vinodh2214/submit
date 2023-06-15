package com.assesment.University.Exceptions;

public class CourseAlreadyExistException extends RuntimeException{
    private String message;
    public CourseAlreadyExistException(){}
    public CourseAlreadyExistException(String msg){
        super(msg);
        this.message=msg;
    }
}
