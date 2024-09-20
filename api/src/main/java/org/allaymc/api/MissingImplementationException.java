package org.allaymc.api;

/**
 * @author daoge_cmd
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
