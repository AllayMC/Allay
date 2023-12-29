package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class BooleanNode extends BaseNode {
    public BooleanNode(String name, CommandNode parent) {
        super(name, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var bool = false;
        if (arg.equals("true")) {
            bool = true;
        } else if (!arg.equals("false")) {
            return false;
        }
        context.putResult(bool);
        context.popArg();
        return true;
    }
}
