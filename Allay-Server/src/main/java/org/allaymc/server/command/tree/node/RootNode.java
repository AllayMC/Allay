package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class RootNode extends BaseNode {

    public RootNode() {
        super("ROOT", null);
    }

    @Override
    public boolean match(CommandContext context) {
        return true;
    }

    @Override
    public CommandNode parent() {
        throw new UnsupportedOperationException("Cannot call parent() on root node");
    }

    @Override
    public CommandNode up(@Range(from = 1, to = Integer.MAX_VALUE) int count) {
        throw new UnsupportedOperationException("Cannot call up() on root node");
    }

    @Override
    public CommandParamData toNetworkData() {
        throw new UnsupportedOperationException();
    }
}
