package com.piankov.auctions.exception;

public class CommandHandleException extends Exception {
    public CommandHandleException() {
        super();
    }

    public CommandHandleException(String message) {
        super(message);
    }

    public CommandHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandHandleException(Throwable cause) {
        super(cause);
    }
}
