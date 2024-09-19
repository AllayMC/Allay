package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.selector.args.SelectorArgument;

/**
 * CoordinateArgument is the base class for args like {@link X}, {@link Y} and {@link Z}.
 */
public abstract class CoordinateArgument implements SelectorArgument {

    @Override
    public int getPriority() {
        return 1;
    }
}
