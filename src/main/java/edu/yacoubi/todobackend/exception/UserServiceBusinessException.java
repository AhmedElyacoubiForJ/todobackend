package edu.yacoubi.todobackend.exception;

public class UserServiceBusinessException extends  RuntimeException {

    public UserServiceBusinessException(String message) {
        super(message);
    }

    public UserServiceBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
