package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class KeyNode extends BaseNode {

    public KeyNode(String key, CommandNode parent) {
        super(key, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (name.equals(arg)) {
            context.putResult(arg);
            context.popArg();
            return true;
        } else {
            return false;
        }
    }
}
