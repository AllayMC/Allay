package org.allaymc.api.utils;

/**
 * IdentifierUtils provides some helper methods for {@link Identifier}.
 *
 * @author daoge_cmd
 */
public final class IdentifierUtils {
    /**
     * Split the given identifier by the given delimiter and create an {@link Identifier} object.
     *
     * @param identifier the identifier.
     * @param delimiter  the delimiter.
     *
     * @return the identifier.
     */
    public static Identifier splitOn(String identifier, String delimiter) {
        return new Identifier(AllayStringUtils.fastTwoPartSplit(identifier, delimiter, Identifier.DEFAULT_NAMESPACE));
    }

    /**
     * Try to parse the given identifier to an {@link Identifier} object.
     *
     * @param identifier the identifier.
     *
     * @return the identifier or {@code null} if the identifier is invalid.
     */
    public static Identifier tryParse(String identifier) {
        try {
            return new Identifier(identifier);
        } catch (InvalidIdentifierException lv) {
            return null;
        }
    }

    /**
     * Create a new identifier from the given namespace and path.
     *
     * @param namespace the namespace.
     * @param path      the path.
     *
     * @return the identifier.
     */
    public static Identifier of(String namespace, String path) {
        try {
            return new Identifier(namespace, path);
        } catch (InvalidIdentifierException lv) {
            return null;
        }
    }

    /**
     * Check if the given identifier is valid.
     *
     * @param identifier the identifier.
     *
     * @return {@code true} if the identifier is valid, otherwise {@code false}.
     */
    public static boolean isValid(String identifier) {
        String[] strings = AllayStringUtils.fastTwoPartSplit(identifier, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        return isNamespaceValid(strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0]) && isPathValid(strings[1]);
    }

    /**
     * Check if the given character is valid.
     *
     * @param c the character.
     *
     * @return {@code true} if the character is valid, otherwise {@code false}.
     */
    public static boolean isCharValid(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

    /**
     * Assert that the given identifier is valid.
     *
     * @param identifier the identifier.
     *
     * @throws InvalidIdentifierException if the identifier is invalid.
     */
    public static void assertValid(String identifier) {
        String[] strings = AllayStringUtils.fastTwoPartSplit(identifier, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        var namespace = strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0];
        var path = strings[1];
        if (!isNamespaceValid(namespace)) {
            throw new InvalidIdentifierException("Non [a-z0-9_.-] character in namespace of location: " + namespace + Identifier.NAMESPACE_SEPARATOR + path);
        }
        if (!isPathValid(path)) {
            throw new InvalidIdentifierException("Non [a-z0-9/._-] character in path of location: " + namespace + Identifier.NAMESPACE_SEPARATOR + path);
        }
    }

    /**
     * Check if the path is valid.
     *
     * @param path the path.
     *
     * @return {@code true} if the path is valid, otherwise {@code false}.
     */
    public static boolean isPathValid(String path) {
        for (int i = 0; i < path.length(); ++i) {
            if (!isPathCharacterValid(path.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the character is a valid path character.
     *
     * @param character the character.
     *
     * @return {@code true} if the character is a valid path character, otherwise {@code false}.
     */
    public static boolean isPathCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9'/* || character == '/'*/ || character == '.';
    }

    /**
     * Check if the namespace is valid.
     *
     * @param namespace the namespace.
     *
     * @return {@code true} if the namespace is valid, otherwise {@code false}.
     */
    public static boolean isNamespaceValid(String namespace) {
        for (int i = 0; i < namespace.length(); ++i) {
            if (!isNamespaceCharacterValid(namespace.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the character is a valid namespace character.
     *
     * @param character the character.
     *
     * @return {@code true} if the character is a valid namespace character, otherwise {@code false}.
     */
    public static boolean isNamespaceCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.';
    }
}
