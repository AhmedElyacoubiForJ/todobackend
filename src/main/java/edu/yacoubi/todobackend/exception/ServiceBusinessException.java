package edu.yacoubi.todobackend.exception;

public class ServiceBusinessException extends  RuntimeException {

    public ServiceBusinessException(String message) {
        super(message);
    }

    public ServiceBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
