package org.allaymc.api.utils.exception;

/**
 * An exception which may be thrown when encountering a container related problem.
 *
 * @author daoge_cmd
 */
public class ContainerException extends RuntimeException {
    public ContainerException() {
        super();
    }

    public ContainerException(String message) {
        super(message);
    }

    public ContainerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerException(Throwable cause) {
        super(cause);
    }

    protected ContainerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
