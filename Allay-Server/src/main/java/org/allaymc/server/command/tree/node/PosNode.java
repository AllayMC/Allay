package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandNode;

/**
 * Allay Project 2024/2/15
 *
 * @author daoge_cmd
 */
public abstract class PosNode<T extends Number> extends BaseNode {
    public PosNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue);
    }

    //TODO
}
