package org.allaymc.api.common.exception;

/**
 * Allay Project 2023/7/2
 *
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
