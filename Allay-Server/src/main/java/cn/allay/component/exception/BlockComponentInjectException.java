package cn.allay.component.exception;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
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
