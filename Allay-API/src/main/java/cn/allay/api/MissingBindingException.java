package cn.allay.api;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public class MissingBindingException extends Exception {
    public MissingBindingException() {
        super();
    }

    public MissingBindingException(String message) {
        super(message);
    }

    public MissingBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingBindingException(Throwable cause) {
        super(cause);
    }

    protected MissingBindingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
