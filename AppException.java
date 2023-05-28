package com.example.studenthousing;

public class AppException extends RuntimeException {

    private int code;

    public int getCode() {
        return code;
    }

    public AppException(String message) {
        this(message, 400);
    }

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }
}