package org.allaymc.api.form;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class FormException extends RuntimeException {
    public FormException(String message) {
        super(message);
    }

    public FormException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormException(Throwable cause) {
        super(cause);
    }

    public FormException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
