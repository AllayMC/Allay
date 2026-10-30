package org.allaymc.server.extension;

import lombok.experimental.StandardException;

/**
 * {@code ExtensionException} reports extension entry-point discovery and
 * construction failures during
 * bootstrap. The extension manager uses this unchecked exception for invalid
 * descriptor entrances, incompatible entry types, and reflection failures;
 * bootstrap treats an uncaught failure as fatal initialization failure.
 *
 * @author daoge_cmd
 */
@StandardException
public class ExtensionException extends RuntimeException {
}
