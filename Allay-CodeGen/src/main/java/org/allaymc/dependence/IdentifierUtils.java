package org.allaymc.dependence;

import static org.allaymc.dependence.StringUtils.fastTwoPartSplit;

/**
 * Some helper for {@link Identifier}
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public final class IdentifierUtils {
    public static Identifier splitOn(String id, String delimiter) {
        return new Identifier(fastTwoPartSplit(id, delimiter, Identifier.DEFAULT_NAMESPACE));
    }


    public static Identifier tryParse(String id) {
        try {
            return new Identifier(id);
        } catch (InvalidIdentifierException lv) {
            return null;
        }
    }


    public static Identifier of(String namespace, String path) {
        try {
            return new Identifier(namespace, path);
        } catch (InvalidIdentifierException lv) {
            return null;
        }
    }

    public static boolean isValid(String id) {
        String[] strings = fastTwoPartSplit(id, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        return isNamespaceValid(strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0]) && isPathValid(strings[1]);
    }

    public static boolean isCharValid(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

    public static void assertValid(String id) {
        String[] strings = fastTwoPartSplit(id, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        var namespace = strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0];
        var path = strings[1];
        if (!isNamespaceValid(namespace)) {
            throw new InvalidIdentifierException("Non [a-z0-9_.-] character in namespace of location: " + namespace + Identifier.NAMESPACE_SEPARATOR + path);
        }
        if (!isPathValid(path)) {
            throw new InvalidIdentifierException("Non [a-z0-9/._-] character in path of location: " + namespace + Identifier.NAMESPACE_SEPARATOR + path);
        }
    }

    public static boolean isPathValid(String path) {
        for (int i = 0; i < path.length(); ++i) {
            if (isPathCharacterValid(path.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isPathCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '/' || character == '.';
    }

    public static boolean isNamespaceValid(String namespace) {
        for (int i = 0; i < namespace.length(); ++i) {
            if (isNamespaceCharacterValid(namespace.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isNamespaceCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.';
    }
}
