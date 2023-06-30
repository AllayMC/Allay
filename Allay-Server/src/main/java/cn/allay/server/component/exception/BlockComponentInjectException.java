package cn.allay.server.component.exception;

import cn.allay.api.component.exception.ComponentInjectException;

/**
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
