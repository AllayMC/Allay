package org.allaymc.api.eventbus;

import lombok.experimental.StandardException;

/**
 * {@code EventException} reports invalid event operations and handler
 * declarations to their caller.
 * The event bus uses this unchecked exception when an annotated method cannot
 * handle an event, and event instances use it when cancellation is requested
 * for a type that does not support cancellation.
 *
 * @author daoge_cmd
 */
@StandardException
public class EventException extends RuntimeException {
}
