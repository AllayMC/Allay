package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

/**
 * Allay Project 2024/1/28
 *
 * @author daoge_cmd
 */
public class EnumIgnoreCaseNode extends EnumNode {

    public EnumIgnoreCaseNode(String name, CommandNode parent, Object defaultValue, String[] values) {
        super(name, parent, defaultValue, values);
    }

    public EnumIgnoreCaseNode(String name, CommandNode parent, Object defaultValue, String enumName, String[] values) {
        super(name, parent, defaultValue, enumName, values);
    }

    @Override
    protected boolean customEquals(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }
}
