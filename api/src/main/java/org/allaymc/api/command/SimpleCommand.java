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

    public static final String DEFAULT_COMMAND_PERMISSION_PREFIX = "command.";

    protected CommandTree commandTree;

    /**
     * Create a new SimpleCommand with the given name and description. The
     * permission to execute this command is simply "command.&lt;command name&gt;".
     *
     * @param name        The name of the command.
     * @param description The description of the command.
     */
    public SimpleCommand(String name, @MayContainTrKey String description) {
        this(name, description, List.of(Permission.createForCommand(name, DEFAULT_COMMAND_PERMISSION_PREFIX + name)));
    }

    /**
     * Create a new SimpleCommand with the given name, description and permissions.
     *
     * @param name        The name of the command.
     * @param description The description of the command.
     * @param permissions The permissions required to execute this command.
     */
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
