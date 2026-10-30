package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

/**
 * {@code EnumIgnoreCaseNode} accepts configured enumeration tokens without case
 * sensitivity while preserving the original token as its result. It inherits
 * in-place lowercase normalization of the supplied array for advertisement; the
 * comparison override permits differently cased input without changing the
 * advertised spellings.
 *
 * @author daoge_cmd
 */
public class EnumIgnoreCaseNode extends EnumNode {

    /**
     * Creates a case-insensitive node with an enumeration name derived from
     * {@code name}. The constructor normalizes and retains the supplied values
     * array, and stores the default without enabling omission.
     */
    public EnumIgnoreCaseNode(String name, CommandNode parent, Object defaultValue, String[] values) {
        super(name, parent, defaultValue, values);
    }

    /**
     * Creates a case-insensitive node with the supplied network enumeration
     * name. The values array is normalized in place and retained, while the
     * stored default applies only after optionality is enabled.
     */
    public EnumIgnoreCaseNode(String name, CommandNode parent, Object defaultValue, String enumName, String[] values) {
        super(name, parent, defaultValue, enumName, values);
    }

    /**
     * Returns whether the two enumeration spellings are equal without case
     * sensitivity.
     */
    @Override
    protected boolean customEquals(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }
}
