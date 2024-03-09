package org.allaymc.api.utils.exception;

/**
 * The potential exception thrown while injecting component implementations into build {@link org.allaymc.api.block.type.BlockType BlockType}
 * <p>
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public class BlockComponentInjectException extends ComponentInjectException {
    public BlockComponentInjectException() {
    }

    public BlockComponentInjectException(String message) {
        super(message);
    }

    public BlockComponentInjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockComponentInjectException(Throwable cause) {
        super(cause);
    }

    public BlockComponentInjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
