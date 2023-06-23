package cn.allay.server.item.type;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/15 <br>
 * Allay Project <br>
 */
public class ItemTypeBuildException extends RuntimeException {
    public ItemTypeBuildException() {
    }

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
