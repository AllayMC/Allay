package cn.allay.server.entity.type;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public class EntityTypeBuildException extends RuntimeException {
    public EntityTypeBuildException() {
    }

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
