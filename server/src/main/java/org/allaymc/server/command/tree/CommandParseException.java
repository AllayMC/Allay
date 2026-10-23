package org.allaymc.server.command.tree;

import lombok.experimental.StandardException;

/**
 * {@code CommandParseException} reports an invalid operation while consuming
 * command arguments or reading parse results. It is unchecked so command-node
 * code can propagate a broken parsing assumption to registry-level error
 * handling, which reports a generic execution failure to the sender.
 *
 * @author daoge_cmd
 */
@StandardException
public class CommandParseException extends RuntimeException {
}
