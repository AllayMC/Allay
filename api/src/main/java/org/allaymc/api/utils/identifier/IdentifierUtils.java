package org.allaymc.api.utils.identifier;

import org.allaymc.api.utils.AllayStringUtils;

/**
 * {@code IdentifierUtils} validates and constructs namespaced identifiers with optional
 * failure sentinels. Component validators accept uppercase ASCII letters and empty components,
 * while {@code isCharValid} is a separate character-screening helper that accepts lowercase
 * letters, separators, and slash characters under different rules.
 *
 * @author daoge_cmd
 */
public final class IdentifierUtils {
    /**
     * Creates an identifier using the first occurrence of the supplied delimiter. A missing or
     * leading delimiter keeps {@code Identifier.DEFAULT_NAMESPACE}; the remaining path is
     * validated
     * by the canonical identifier constructor without trimming or case changes.
     */
    public static Identifier splitOn(String identifier, String delimiter) {
        return new Identifier(AllayStringUtils.fastTwoPartSplit(identifier, delimiter, Identifier.DEFAULT_NAMESPACE));
    }

    /**
     * Parses an identifier and returns {@code null} when canonical character validation rejects
     * it. Only {@code InvalidIdentifierException} is suppressed; {@code null} input and other
     * construction failures still propagate instead of producing that sentinel.
     */
    public static Identifier tryParse(String identifier) {
        try {
            return new Identifier(identifier);
        } catch (InvalidIdentifierException lv) {
            // Character rejection is the recoverable parse result here. Preserve other
            // failures so callers can distinguish invalid names from missing inputs or setup errors.
            return null;
        }
    }

    /**
     * Creates an identifier from separate namespace and path values, returning {@code null} for
     * rejected characters. Empty namespace defaulting still applies, but {@code null} components
     * and other construction failures are not suppressed by this helper.
     */
    public static Identifier of(String namespace, String path) {
        try {
            return new Identifier(namespace, path);
        } catch (InvalidIdentifierException lv) {
            // Invalid component characters are an expected construction failure for this
            // helper. Return absence for that case without suppressing unrelated runtime failures.
            return null;
        }
    }

    /**
     * Returns whether defaulted namespace and path components pass canonical character validation.
     * Empty paths are accepted and uppercase ASCII letters are allowed, while {@code null}
     * input fails rather than returning {@code false}.
     */
    public static boolean isValid(String identifier) {
        String[] strings = AllayStringUtils.fastTwoPartSplit(identifier, Identifier.NAMESPACE_SEPARATOR, Identifier.DEFAULT_NAMESPACE);
        return isNamespaceValid(strings[0].isEmpty() ? Identifier.DEFAULT_NAMESPACE : strings[0]) && isPathValid(strings[1]);
    }

    /**
     * Returns whether a character belongs to this helper's lowercase identifier-input set.
     * This check allows colon and slash but rejects uppercase letters, so it is not equivalent
     * to canonical namespace or path validation.
     */
    public static boolean isCharValid(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

    /**
     * Validates the defaulted namespace and path without constructing an identifier. The rules
     * match canonical component validation, including uppercase letters and empty paths; invalid
     * characters raise {@code InvalidIdentifierException}, while {@code null} input is not
     * accepted.
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
     * Returns whether every path character passes {@code isPathCharacterValid}. Empty paths pass
     * because no character is rejected, while {@code null} fails during length access rather than
     * returning a validation result.
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
     * Returns whether a path character is an ASCII letter, digit, underscore, hyphen, or period.
     */
    public static boolean isPathCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.';
    }

    /**
     * Returns whether every namespace character passes {@code isNamespaceCharacterValid}. Empty
     * namespaces pass this character test, while {@code null} fails during length access;
     * namespace
     * defaulting is the identifier constructor's separate responsibility.
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
     * Returns whether a namespace character is an ASCII letter, digit, underscore, hyphen, or
     * period.
     */
    public static boolean isNamespaceCharacterValid(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.';
    }
}
