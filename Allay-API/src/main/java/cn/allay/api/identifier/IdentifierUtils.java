package cn.allay.api.identifier;

import org.jetbrains.annotations.Nullable;

import static cn.allay.api.utils.StringUtils.fastTwoPartSplit;

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

    @Nullable
    public static Identifier tryParse(String id) {
        try {
            return new Identifier(id);
        } catch (InvalidIdentifierException lv) {
            return null;
        }
    }

    @Nullable
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

    static boolean isPathValid(String path) {
        for (int i = 0; i < path.length(); ++i) {
            if (isPathCharacterValid(path.charAt(i))) continue;
            return false;
        }
        return true;
    }

    static boolean isPathCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= '0' && character <= '9' || character == '/' || character == '.';
    }

    static boolean isNamespaceValid(String namespace) {
        for (int i = 0; i < namespace.length(); ++i) {
            if (isNamespaceCharacterValid(namespace.charAt(i))) continue;
            return false;
        }
        return true;
    }

    static boolean isNamespaceCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= '0' && character <= '9' || character == '.';
    }
}
