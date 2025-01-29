package com.effigopracticeproject.learning_portal.exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message)
    {
        super(message);
    }
}
