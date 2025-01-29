package com.effigopracticeproject.learning_portal.exceptions;

public class NoRegisteredCourseFoundException extends RuntimeException {
    public NoRegisteredCourseFoundException(String message) {
        super(message);
    }
}