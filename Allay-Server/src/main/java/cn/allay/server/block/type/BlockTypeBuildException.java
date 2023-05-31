package cn.allay.server.block.type;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public class BlockTypeBuildException extends RuntimeException {
    public BlockTypeBuildException() {
    }

    public BlockTypeBuildException(String message) {
        super(message);
    }

    public BlockTypeBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockTypeBuildException(Throwable cause) {
        super(cause);
    }

    public BlockTypeBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
