package org.allaymc.server.entity.type;

import lombok.experimental.StandardException;

/**
 * {@code EntityTypeBuildException} reports failure while preparing an entity
 * type or constructing one of its instances. The type builder wraps reflection
 * setup errors and its captured factory wraps constructor failures, preserving
 * their causes for callers diagnosing invalid entity definitions or component
 * initialization.
 *
 * @author daoge_cmd
 */
@StandardException
public class EntityTypeBuildException extends RuntimeException {
}
