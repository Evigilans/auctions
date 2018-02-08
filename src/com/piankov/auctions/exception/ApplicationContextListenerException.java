package com.piankov.auctions.exception;

public class ApplicationContextListenerException extends Exception {
    public ApplicationContextListenerException() {
        super();
    }

    public ApplicationContextListenerException(String message) {
        super(message);
    }

    public ApplicationContextListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationContextListenerException(Throwable cause) {
        super(cause);
    }
}
