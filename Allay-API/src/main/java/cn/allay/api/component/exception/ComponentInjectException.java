package cn.allay.api.component.exception;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * Thrown when component injection encounters a problem
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
