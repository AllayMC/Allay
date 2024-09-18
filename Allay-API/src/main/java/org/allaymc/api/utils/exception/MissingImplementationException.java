package org.allaymc.api.utils.exception;

import org.allaymc.api.AllayAPI;

/**
 * This exception is thrown when the API module is not fully implemented <br/>
 * <p>
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
