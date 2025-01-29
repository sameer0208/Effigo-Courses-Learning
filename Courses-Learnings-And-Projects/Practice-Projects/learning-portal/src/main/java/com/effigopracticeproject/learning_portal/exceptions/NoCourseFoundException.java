package com.effigopracticeproject.learning_portal.exceptions;

public class NoCourseFoundException extends RuntimeException {
    public NoCourseFoundException(String message) {
        super(message);
    }
}