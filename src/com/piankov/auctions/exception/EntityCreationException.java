package com.piankov.auctions.exception;

public class EntityCreationException extends Exception {
    public EntityCreationException() {
        super();
    }

    public EntityCreationException(String message) {
        super(message);
    }

    public EntityCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityCreationException(Throwable cause) {
        super(cause);
    }
}
