package org.allaymc.server.cmdv2.tree.node;

import org.allaymc.api.cmdv2.tree.CommandContext;
import org.allaymc.api.cmdv2.tree.CommandNode;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class StringNode extends BaseNode {

    public StringNode(String name, CommandNode parent) {
        super(name, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        context.popAndPutArgToResult();
        return true;
    }
}
