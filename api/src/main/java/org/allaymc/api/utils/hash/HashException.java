package org.allaymc.api.utils.hash;

import lombok.experimental.StandardException;

/**
 * {@code HashException} reports a failure while serializing data for hash
 * calculation. Hash utilities wrap the underlying I/O failure with this
 * unchecked exception so callers cannot mistake an unavailable hash for a valid
 * computed value; the cause retains the serialization diagnostic.
 *
 * @author daoge_cmd
 */
@StandardException
public class HashException extends RuntimeException {
}
