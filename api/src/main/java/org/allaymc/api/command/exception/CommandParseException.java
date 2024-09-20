package org.allaymc.api.command.exception;

/**
 * @author daoge_cmd
 */
public class CommandParseException extends RuntimeException {
    public CommandParseException(String message) {
        super(message);
    }

    public CommandParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParseException(Throwable cause) {
        super(cause);
    }

    public CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
