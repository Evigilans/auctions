package com.piankov.auctions.exception;

public class ActionPerformingException extends Exception {
    public ActionPerformingException() {
        super();
    }

    public ActionPerformingException(String message) {
        super(message);
    }

    public ActionPerformingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionPerformingException(Throwable cause) {
        super(cause);
    }
}
