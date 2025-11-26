package org.allaymc.server.command.tree;

import lombok.Getter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.server.command.tree.node.RootNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public class AllayCommandTree implements CommandTree {

    protected Command command;
    protected CommandNode root = new RootNode();

    private AllayCommandTree(Command command) {
        this.command = command;
    }

    public static CommandTree create(Command command) {
        return new AllayCommandTree(command);
    }

    @Override
    public List<CommandNode> getLeaves() {
        var leaves = new ArrayList<CommandNode>();
        findLeaf(root, leaves);
        return leaves;
    }

    protected void findLeaf(CommandNode node, List<CommandNode> dest) {
        if (node.isLeaf()) dest.add(node);
        else node.getLeaves().forEach(leaf -> findLeaf(leaf, dest));
    }

    @Override
    public CommandResult parse(CommandSender sender, String[] args) {
        var context = new AllayCommandContext(command, sender, args);
        return parse0(root, context);
    }

    protected CommandResult parse0(CommandNode node, CommandContext context) {
        if (node.isLeaf()) {
            var executor = node.getExecutor();
            var onRedirect = node.getOnRedirect();

            if (executor != null) {
                if (context.haveUnhandledArg()) {
                    context.addSyntaxError(context.getCurrentArgIndex() + 1);
                    return context.fail();
                }

                return executor.apply(context);
            } else if (onRedirect != null) {
                onRedirect.accept(context);
                context.clearResults();
                node = root;
            }
        }

        var nextNode = node.nextNode(context);
        if (nextNode == null) {
            context.addSyntaxError();
            return context.fail();
        } else if (!nextNode.checkPermissions(context.getSender())) {
            context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_UNKNOWN, context.queryArg(context.getCurrentArgIndex() - 1));
            return context.fail();
        } else {
            context.addPermissions(nextNode.getPermissions());
            return parse0(nextNode, context);
        }
    }
}
