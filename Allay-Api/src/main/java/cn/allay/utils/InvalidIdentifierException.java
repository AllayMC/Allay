package cn.allay.utils;

public class InvalidIdentifierException extends RuntimeException {
    public InvalidIdentifierException(String message) {
        super(message);
    }

    public InvalidIdentifierException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

