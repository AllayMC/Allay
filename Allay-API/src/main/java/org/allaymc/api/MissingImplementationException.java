package org.allaymc.api;

/**
 * This exception is thrown when the API module is not fully implemented <br/>
 * <p>
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 * @see AllayAPI
 */
public class MissingImplementationException extends Exception {
    public MissingImplementationException() {
        super();
    }

    public MissingImplementationException(String message) {
        super(message);
    }

    public MissingImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingImplementationException(Throwable cause) {
        super(cause);
    }

    protected MissingImplementationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
