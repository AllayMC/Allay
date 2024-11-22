package org.allaymc.server.blockentity.type;

/**
 * @author daoge_cmd
 */
public class BlockEntityTypeBuildException extends RuntimeException {
    public BlockEntityTypeBuildException(String message) {
        super(message);
    }

    public BlockEntityTypeBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockEntityTypeBuildException(Throwable cause) {
        super(cause);
    }

    public BlockEntityTypeBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
