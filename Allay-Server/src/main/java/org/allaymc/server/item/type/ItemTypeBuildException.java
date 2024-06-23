package org.allaymc.server.item.type;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public class ItemTypeBuildException extends RuntimeException {
    public ItemTypeBuildException(String message) {
        super(message);
    }

    public ItemTypeBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemTypeBuildException(Throwable cause) {
        super(cause);
    }

    public ItemTypeBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
