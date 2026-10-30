package org.allaymc.dependence;

import static org.allaymc.dependence.StringUtils.fastTwoPartSplit;

/**
 * {@code IdentifierUtils} parses generator identifiers and checks their
 * namespace and path characters. Full-component validation accepts uppercase
 * letters and empty components, while the single-character input filter accepts
 * lowercase letters only. Optional parsing converts identifier-validation
 * failures to {@code null} without suppressing unrelated errors such as
 * {@code null} input.
 *
 * @author Cool_Loong
 */
public final class IdentifierUtils {
    /**
     * Parses an identifier using the first occurrence of a literal delimiter. A
     * missing or leading delimiter selects the default namespace; the resulting
     * namespace and path are validated by the identifier constructor.
     */
    public static Identifier splitOn(String id, String delimiter) {
        return new Identifier(fastTwoPartSplit(id, delimiter, Identifier.DEFAULT_NAMESPACE));
    }


    /**
     * Parses a colon-separated identifier, returning {@code null} for invalid
     * component characters. Missing namespaces use the identifier's default;
     * {@code null} input still raises {@link NullPointerException} because only
     * validation failures are converted to an absent result.
     */
    public static Identifier tryParse(String id) {
        try {
            return new Identifier(id);
        } catch (InvalidIdentifierException lv) {
            // Treat invalid resource spelling as an absent optional identifier;
            // unrelated programming errors still propagate to the generator caller.
            return null;
        }
    }


    /**
     * Creates an identifier from separate components, returning {@code null}
     * for invalid characters. An empty namespace uses the default, and an empty
     * path is accepted; {@code null} components still fail rather than becoming
     * {@code null} results through this helper.
     */
    public static Identifier of(String namespace, String path) {
        try {
            return new Identifier(namespace, path);
        } catch (InvalidIdentifierException lv) {
            // Convert component-validation failure to the optional result while
            // leaving null-input errors visible to callers assembling generator data.
            return null;
        }
    }

    /**
     * Returns whether both parsed identifier components contain accepted
     * characters. Missing or empty namespaces select the default namespace;
     * empty paths are accepted, and letters are checked without requiring
     * lowercase spelling.
     */
    public static boolean isValid(String id) {
        String[] strings = fastTwoPartSplit(id, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        return isNamespaceValid(strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0]) && isPathValid(strings[1]);
    }

    /**
     * Returns whether a character is accepted by the lowercase input filter.
     * This includes the colon separator and path slash, but excludes uppercase
     * letters even though complete namespace and path validation accepts them.
     */
    public static boolean isCharValid(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

    /**
     * Validates both components of a colon-separated identifier without
     * constructing one. Missing namespaces select the default, and empty paths
     * remain valid. The first invalid component raises an
     * {@link InvalidIdentifierException}; uppercase letters are accepted
     * despite the lowercase character ranges shown in its diagnostic message.
     */
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

    /**
     * Returns whether every character belongs to the resource-path alphabet.
     * Letters may be uppercase or lowercase, and slashes are accepted alongside
     * digits, underscores, hyphens, and periods. An empty path passes this
     * check.
     */
    public static boolean isPathValid(String path) {
        for (int i = 0; i < path.length(); ++i) {
            if (isPathCharacterValid(path.charAt(i))) continue;
            return false;
        }
        return true;
    }

    /**
     * Returns whether a character belongs to the resource-path alphabet.
     * Accepted characters are ASCII letters of either case, digits,
     * underscores, hyphens, periods, and slashes; namespace separators are not
     * path characters.
     */
    public static boolean isPathCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '/' || character == '.';
    }

    /**
     * Returns whether every character belongs to the namespace alphabet. Empty
     * namespaces pass this character check, leaving default substitution to
     * identifier construction. ASCII letters of either case, digits,
     * underscores, hyphens, and periods are accepted.
     */
    public static boolean isNamespaceValid(String namespace) {
        for (int i = 0; i < namespace.length(); ++i) {
            if (isNamespaceCharacterValid(namespace.charAt(i))) continue;
            return false;
        }
        return true;
    }

    /**
     * Returns whether a character belongs to the resource-namespace alphabet.
     * Accepted characters are ASCII letters of either case, digits,
     * underscores, hyphens, and periods; slashes and namespace separators are
     * rejected.
     */
    public static boolean isNamespaceCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.';
    }
}
