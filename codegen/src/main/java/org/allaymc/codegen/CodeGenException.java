package org.allaymc.codegen;

import lombok.experimental.StandardException;

/**
 * {@code CodeGenException} reports a failure while reading or transforming
 * inputs for source generation. Generator helpers wrap checked input failures
 * in this runtime exception so their entry points can abort without returning a
 * partial result as success. Lombok supplies the usual message and cause
 * constructors.
 *
 * @author daoge_cmd
 */
@StandardException
public class CodeGenException extends RuntimeException {
}
