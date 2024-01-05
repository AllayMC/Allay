package org.allaymc.api.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class RootNode extends BaseNode {

    public RootNode() {
        super("ROOT", null, null);
    }

    @Override
    public boolean match(CommandContext context) {
        return true;
    }

    @Override
    public Object getDefaultValue() {
        throw new UnsupportedOperationException("Cannot call getDefaultValue() on root node");
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

    @Override
    public int getMaxArgCost() {
        throw new UnsupportedOperationException("Cannot call getMaxArgCost() on root node");
    }

    @Override
    public int getMaxArgCostBranch() {
        throw new UnsupportedOperationException("Cannot call getMaxArgCost() on root node");
    }

    @Override
    public int getMinArgCostBranch() {
        throw new UnsupportedOperationException("Cannot call getMinArgCost() on root node");
    }
}
