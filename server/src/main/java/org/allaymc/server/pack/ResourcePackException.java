package org.allaymc.server.pack;

import lombok.experimental.StandardException;

/**
 * {@code ResourcePackException} reports unchecked failures from pack archive
 * loaders. Loader implementations wrap file-system and stream failures with
 * this exception when their API path does not expose a checked exception.
 * Lombok supplies the standard constructors for retaining a message or cause.
 *
 * @author daoge_cmd
 */
@StandardException
public class ResourcePackException extends RuntimeException {
}
