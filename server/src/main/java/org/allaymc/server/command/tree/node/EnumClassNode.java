package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

import java.util.Locale;

/**
 * @author daoge_cmd
 */
public class EnumClassNode extends EnumIgnoreCaseNode {

    protected Class<? extends Enum<?>> enumClass;

    public <T extends Enum<?>> EnumClassNode(String name, CommandNode parent, T defaultValue, Class<T> enumClass) {
        super(name, parent, defaultValue, toStringArray(enumClass));
        this.enumClass = enumClass;
    }

    public <T extends Enum<?>> EnumClassNode(String name, CommandNode parent, T defaultValue, String enumName, Class<T> enumClass) {
        super(name, parent, defaultValue, enumName, toStringArray(enumClass));
        this.enumClass = enumClass;
    }

    protected static String[] toStringArray(Class<? extends Enum<?>> enumClass) {
        var enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name().toLowerCase(Locale.ROOT);
        }
        return values;
    }

    @Override
    protected Object argToResult(String arg) {
        for (var e : enumClass.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(arg)) {
                return e;
            }
        }
        return null;
    }
}
