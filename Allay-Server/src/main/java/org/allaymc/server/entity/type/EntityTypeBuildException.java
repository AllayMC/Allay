package org.allaymc.server.entity.type;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public class EntityTypeBuildException extends RuntimeException {
    public EntityTypeBuildException(String message) {
        super(message);
    }

    public EntityTypeBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityTypeBuildException(Throwable cause) {
        super(cause);
    }

    public EntityTypeBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
