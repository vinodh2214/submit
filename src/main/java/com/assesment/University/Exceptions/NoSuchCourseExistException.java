package com.assesment.University.Exceptions;

public class NoSuchCourseExistException extends RuntimeException {
    private String message;

    public NoSuchCourseExistException(Long id) {
    }

    public NoSuchCourseExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}