package org.allaymc.api.utils.exception;

/**
 * @author daoge_cmd
 */
public class WorldStorageException extends RuntimeException {
    public WorldStorageException() {
    }

    public WorldStorageException(String message) {
        super(message);
    }

    public WorldStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorldStorageException(Throwable cause) {
        super(cause);
    }

    public WorldStorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
