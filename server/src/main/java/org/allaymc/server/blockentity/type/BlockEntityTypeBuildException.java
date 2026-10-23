package org.allaymc.server.blockentity.type;

import lombok.experimental.StandardException;

/**
 * {@code BlockEntityTypeBuildException} reports reflection failures while
 * preparing or invoking block-entity constructors. The type builder wraps
 * constructor discovery failures, while a built type wraps failures from its
 * captured constructor invocation. Registry registration failures propagate
 * separately and are not converted to this exception.
 *
 * @author daoge_cmd
 */
@StandardException
public class BlockEntityTypeBuildException extends RuntimeException {
}
