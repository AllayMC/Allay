package org.allaymc.server.utils;

/**
 * @author daoge_cmd
 */
public class ComponentInjectException extends RuntimeException {
    public ComponentInjectException() {
    }

    public ComponentInjectException(String message) {
        super(message);
    }

    public ComponentInjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentInjectException(Throwable cause) {
        super(cause);
    }

    public ComponentInjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
