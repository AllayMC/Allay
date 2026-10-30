package org.allaymc.server.datastruct.palette;

import lombok.experimental.StandardException;

/**
 * {@code PaletteException} reports palette format, capacity, or I/O failures as an
 * unchecked exception. Readers use it for incompatible representation headers, while
 * writers wrap NBT I/O errors with their original cause through the generated exception
 * constructors.
 * @author daoge_cmd
 */
@StandardException
public class PaletteException extends RuntimeException {
}
