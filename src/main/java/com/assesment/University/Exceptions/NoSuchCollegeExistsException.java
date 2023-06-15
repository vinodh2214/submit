package com.assesment.University.Exceptions;

public class NoSuchCollegeExistsException extends RuntimeException {
    private String message;

    public NoSuchCollegeExistsException() {
    }

    public NoSuchCollegeExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}