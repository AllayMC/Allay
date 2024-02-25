package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.selector.args.SelectorArgument;

/**
 * args like x,y,z.
 */


public abstract class CoordinateArgument implements SelectorArgument {

    @Override
    public int getPriority() {
        return 1;
    }
}
