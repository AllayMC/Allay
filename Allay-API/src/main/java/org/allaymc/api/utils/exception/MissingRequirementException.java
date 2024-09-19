package org.allaymc.api.utils.exception;

/**
 * @author CoolLoong
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
