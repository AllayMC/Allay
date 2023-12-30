package org.allaymc.server.command.tree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.command.CommandResult;
import org.allaymc.server.command.tree.node.RootNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2023/12/29
 *
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
        if (node.isLeaf()) {
            dest.add(node);
        } else {
            for (var leaf : node.getLeaves()) {
                findLeaf(leaf, dest);
            }
        }
    }

    @Override
    public CommandResult parse(CommandSender sender, String[] args) {
        var context = new AllayCommandContext(command, sender, args);
        return parse0(root, context);
    }

    protected CommandResult parse0(CommandNode node, CommandContext context) {
        if (node.isLeaf()) {
            if (context.haveUnhandledArg()) {
                context.addSyntaxError();
                return context.failed();
            }
            return node.getExecutor().apply(context);
        }
        var nextNode = node.nextNode(context);
        if (nextNode == null) {
            context.addSyntaxError();
            return context.failed();
        } else return parse0(nextNode, context);
    }
}
