package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class IntNode extends BaseNode {

    public IntNode(String name, CommandNode parent) {
        super(name, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var number = 0;
        try {
            number = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        context.putResult(number);
        context.popArg();
        return true;
    }
}
