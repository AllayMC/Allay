package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandTree;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public abstract class SimpleCommand extends BaseCommand {

    protected CommandTree commandTree;

    public SimpleCommand(String name, String description) {
        super(name, description);
        this.commandTree = CommandTree.create();
        prepareCommandTree(this.commandTree);
        buildOverloadsFromCommandTree();
    }

    public abstract void prepareCommandTree(CommandTree commandTree);

    protected void buildOverloadsFromCommandTree() {
        var leaves = commandTree.getLeaves();
        for (var leaf : leaves) {
            var paramArray = new CommandParamData[leaf.depth()];
            var node = leaf;
            var index = leaf.depth() - 1;
            while (node.depth() != 0) {
                paramArray[index] = node.toNetworkData();
                node = node.root();
                index--;
            }
            overloads.add(paramArray);
        }
    }
}
