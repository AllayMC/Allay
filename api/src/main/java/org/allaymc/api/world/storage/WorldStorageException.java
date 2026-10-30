package org.allaymc.api.world.storage;

import lombok.experimental.StandardException;

/**
 * {@code WorldStorageException} reports
 * a failure while opening, reading, writing, or closing world storage.
 * Storage implementations use this unchecked exception to retain an underlying
 * cause or describe an unsupported format; Lombok supplies the standard message
 * and cause constructors.
 *
 * @author daoge_cmd
 */
@StandardException
public class WorldStorageException extends RuntimeException {
}
