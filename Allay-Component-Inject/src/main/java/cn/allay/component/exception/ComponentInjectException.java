package cn.allay.component.exception;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 *
 * Exception thrown when the component injection fails
 */
public class ComponentInjectException extends RuntimeException {
    public ComponentInjectException() {
    }

    public ComponentInjectException(String message) {
        super(message);
    }

    public ComponentInjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentInjectException(Throwable cause) {
        super(cause);
    }

    public ComponentInjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
