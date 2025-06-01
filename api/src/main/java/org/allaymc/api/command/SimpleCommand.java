package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.permission.Permission;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.List;

/**
 * SimpleCommand is a subclass of {@link Command} that provide command tree supporting.
 * <p>
 * In most of the cases, you should use this class to create a new command.
 *
 * @author daoge_cmd
 */
public abstract class SimpleCommand extends BaseCommand {

    protected CommandTree commandTree;

    public SimpleCommand(String name, @MayContainTrKey String description) {
        this(name, description, List.of(Command.createPermissionForCommand(name)));
    }

    public SimpleCommand(String name, @MayContainTrKey String description, List<Permission> permissions) {
        super(name, description, permissions);
        this.commandTree = CommandTree.create(this);
        prepareCommandTree(this.commandTree);
        buildOverloadsFromCommandTree();
    }

    /**
     * Prepare the command tree for this command.
     *
     * @param tree The command tree to prepare.
     */
    public abstract void prepareCommandTree(CommandTree tree);

    protected void buildOverloadsFromCommandTree() {
        for (var leaf : commandTree.getLeaves()) {
            var paramArray = new CommandParamData[leaf.depth()];
            var node = leaf;
            var index = leaf.depth() - 1;
            while (!node.isRoot()) {
                paramArray[index] = node.toNetworkData();
                node = node.parent();
                index--;
            }
            overloads.add(paramArray);
        }
    }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        return commandTree.parse(sender, args);
    }
}
