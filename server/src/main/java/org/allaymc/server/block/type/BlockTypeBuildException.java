package org.allaymc.server.block.type;

import lombok.experimental.StandardException;

/**
 * {@code BlockTypeBuildException} reports a block-type builder failure that
 * prevents construction or registration from completing normally. Builder
 * operations attach descriptive messages and, for reflected behavior construction,
 * the underlying cause; the generated constructors support these unchecked
 * error-reporting forms.
 *
 * @author daoge_cmd
 */
@StandardException
public class BlockTypeBuildException extends RuntimeException {
}
