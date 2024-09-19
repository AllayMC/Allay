package org.allaymc.server.block.type;

/**
 * @author daoge_cmd
 */
public class BlockTypeBuildException extends RuntimeException {
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
