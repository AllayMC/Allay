package org.allaymc.api.container;

import lombok.experimental.StandardException;

/**
 * {@code ContainerException} reports an invalid container operation through an unchecked
 * exception. Container holders use it when a container with the same type identity is
 * already registered, allowing callers to distinguish that domain failure from ordinary
 * item access errors.
 *
 * @author daoge_cmd
 */
@StandardException
public class ContainerException extends RuntimeException {
}
