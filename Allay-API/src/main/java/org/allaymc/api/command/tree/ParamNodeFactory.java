package org.allaymc.api.command.tree;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface ParamNodeFactory {
    CommandNode key(String key, String defaultValue);

    default CommandNode key(String key) {
        return key(key, "");
    }

    CommandNode str(String name, String defaultValue);

    default CommandNode str(String name) {
        return str(name, "");
    }

    CommandNode intNum(String name, int defaultValue);

    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    CommandNode floatNum(String name, float defaultValue);

    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    CommandNode doubleNum(String name, double defaultValue);

    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    CommandNode bool(String name, boolean defaultValue);

    default CommandNode bool(String name) {
        return bool(name, false);
    }

    CommandNode enums(String name, String defaultValue, String[] enums);

    default CommandNode enums(String name, String... enums) {
        return enums(name, "", enums);
    }

    default CommandNode enums(String name, Class<? extends Enum<?>> enumClass) {
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name();
        }
        return enums(name, values);
    }
}
