package org.allaymc.server.cmdv2.tree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.allaymc.api.cmdv2.CommandSender;
import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;
import org.allaymc.api.cmdv2.tree.CommandTree;
import org.allaymc.api.cmdv2.CommandResult;
import org.allaymc.server.cmdv2.tree.node.RootNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
@Getter
@NoArgsConstructor
public class AllayCommandTree implements CommandTree {

    protected CommandNode root = new RootNode();

    public static CommandTree create() {
        return new AllayCommandTree();
    }

    @Override
    public CommandResult parse(CommandSender sender, String[] args) {
        var context = new AllayCommandContext(sender, args);
        return parse0(root, context);
    }

    protected CommandResult parse0(CommandNode node, CommandContext context) {
        if (node.isLeaf()) {
            return node.getExecutor().apply(context);
        }
        var nextNode = node.nextNode(context);
        if (nextNode == null) {
            // 子节点没有匹配项
            // TODO: log
            return CommandResult.FAILED;
        } else return parse0(nextNode, context);
    }
}
