package com.assesment.University.Exceptions;


public class NoSuchDepartmentExistException extends RuntimeException {
    private String message;

    public NoSuchDepartmentExistException(Long id) {
    }

    public NoSuchDepartmentExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
