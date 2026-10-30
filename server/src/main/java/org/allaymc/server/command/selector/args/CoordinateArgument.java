package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.selector.SelectorArgument;

/**
 * {@code CoordinateArgument} groups handlers that update the selection origin
 * before spatial filtering. Its priority places coordinate changes ahead of
 * box-range and radius handlers, so those later handlers read the adjusted
 * origin rather than the sender's original coordinates.
 */
public abstract class CoordinateArgument implements SelectorArgument {

    /**
     * Returns priority one so coordinate handlers precede spatial range
     * predicates.
     */
    @Override
    public int getPriority() {
        return 1;
    }
}
