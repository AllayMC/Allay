package org.allaymc.server.entity.type;

/**
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
