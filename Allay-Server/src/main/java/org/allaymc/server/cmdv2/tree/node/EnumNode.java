package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class EnumNode extends BaseNode {
    protected String[] values;

    public EnumNode(String name, CommandNode parent, String... values) {
        super(name, parent);
        this.values = values;
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        boolean matched = false;
        for (String value : values) {
            if (value.equals(arg)) {
                matched = true;
                break;
            }
        }
        if (matched) {
            context.putResult(arg);
            context.popArg();
            return true;
        } else {
            return false;
        }
    }

}
