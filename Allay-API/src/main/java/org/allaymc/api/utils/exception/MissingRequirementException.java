package org.allaymc.api.utils.exception;

import org.allaymc.api.AllayAPI;

/**
 * This exception is thrown in the API where the implementation is not required<br/>
 * <p>
 * Allay Project 2023/11/24
 *
 * @author CoolLoong
 * @see AllayAPI
 */
public class MissingRequirementException extends RuntimeException {
    public MissingRequirementException() {
        super();
    }

    public MissingRequirementException(String message) {
        super(message);
    }

    public MissingRequirementException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequirementException(Throwable cause) {
        super(cause);
    }

    protected MissingRequirementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
