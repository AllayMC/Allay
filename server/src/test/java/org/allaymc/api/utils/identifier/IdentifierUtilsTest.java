package org.allaymc.api.utils.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code IdentifierUtilsTest} verifies identifier helper parsing, recoverable failures, component
 * validation, and character-screening differences.
 *
 * @author daoge_cmd
 */
class IdentifierUtilsTest {

    // idStr and idStr2 contain the same components with different separators. Split tests
    // supply matching delimiters, while canonical validation rejects the semicolon-containing representation.
    /**
     * Colon-separated identifier test fixture.
     */
    static final String idStr = "pnx:love";
    /**
     * Semicolon-separated identifier test fixture.
     */
    static final String idStr2 = "pnx;love";

    /**
     * Parses equivalent component text with colon and semicolon delimiters. The resulting
     * identifiers must compare equal after each helper splits on its caller-selected separator.
     */
    @Test
    void testSplitOn() {
        var id1 = IdentifierUtils.splitOn(idStr, ":");
        var id2 = IdentifierUtils.splitOn(idStr2, ";");
        assertEquals(id1, id2);
    }

    /**
     * Parses an unqualified name with a delimiter that is absent. The helper must retain the whole
     * path and supply the public default namespace.
     */
    @Test
    void testSplitOnWithoutDelimiter() {
        var id = IdentifierUtils.splitOn("stone", ":");
        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    /**
     * Parses a valid namespaced identifier through the recoverable parsing helper. The result must
     * be present and retain both expected namespace and path components.
     */
    @Test
    void testTryParse() {
        var id = IdentifierUtils.tryParse(idStr);
        assertNotNull(id);
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    /**
     * Parses an unqualified name through the recoverable parsing helper. The result must be
     * present, use the default namespace, and retain the supplied path.
     */
    @Test
    void testTryParseWithDefaultNamespace() {
        var id = IdentifierUtils.tryParse("stone");
        assertNotNull(id);
        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    /**
     * Parses names containing invalid namespace or path characters through the recoverable helper.
     * Each tested character rejection must return {@code null} rather than escaping as an identifier
     * exception.
     */
    @Test
    void testTryParseInvalidReturnsNull() {
        assertNull(IdentifierUtils.tryParse("invalid namespace:path"));
        assertNull(IdentifierUtils.tryParse("namespace:invalid path"));
        assertNull(IdentifierUtils.tryParse("invalid@:path"));
    }

    /**
     * Creates an identifier from separate valid namespace and path values. The recoverable
     * construction helper must return a present value retaining both supplied components
     * unchanged.
     */
    @Test
    void testOf() {
        var id = IdentifierUtils.of("pnx", "love");
        assertNotNull(id);
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    /**
     * Creates identifiers from separate components containing invalid characters. The recoverable
     * helper must return {@code null} for each tested rejection rather than exposing a partially valid
     * identifier.
     */
    @Test
    void testOfInvalidReturnsNull() {
        assertNull(IdentifierUtils.of("invalid namespace", "path"));
        assertNull(IdentifierUtils.of("namespace", "invalid path"));
        assertNull(IdentifierUtils.of("invalid@", "path"));
    }

    /**
     * Checks one valid colon-separated identifier and one semicolon-containing input.
     * Whole-identifier validation must accept the canonical separator and reject punctuation
     * outside the component grammar.
     */
    @Test
    void testIsValid() {
        assertTrue(IdentifierUtils.isValid(idStr));
        assertFalse(IdentifierUtils.isValid(idStr2));
    }

    /**
     * Checks an unqualified path and a colon-prefixed path. Validation must accept both
     * representations because their missing or empty namespace resolves to the public default.
     */
    @Test
    void testIsValidWithDefaultNamespace() {
        assertTrue(IdentifierUtils.isValid("stone"));
        assertTrue(IdentifierUtils.isValid(":stone"));
    }

    /**
     * Checks spaces and invalid punctuation in namespace and path positions. Whole-identifier
     * validation must return {@code false} for every tested component containing a rejected character.
     */
    @Test
    void testIsValidWithInvalidCharacters() {
        assertFalse(IdentifierUtils.isValid("ns:path with space"));
        assertFalse(IdentifierUtils.isValid("ns@invalid:path"));
        assertFalse(IdentifierUtils.isValid("ns:path@invalid"));
    }

    /**
     * Asserts one valid identifier and one semicolon-containing identifier. The valid name must
     * pass while the invalid component punctuation raises the identifier-specific validation
     * exception.
     */
    @Test
    void testAssertValid() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(idStr));
        assertThrows(InvalidIdentifierException.class, () -> IdentifierUtils.assertValid(idStr2));
    }

    /**
     * Asserts an unqualified path and a path with a leading separator. Both must pass after
     * namespace defaulting rather than requiring an explicit namespace in the input.
     */
    @Test
    void testAssertValidWithDefaultNamespace() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid("stone"));
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(":stone"));
    }

    /**
     * Asserts an identifier whose namespace contains a space. The assertion helper must raise the
     * identifier-specific exception rather than accepting the invalid namespace text.
     */
    @Test
    void testAssertValidThrowsForInvalidNamespace() {
        assertThrows(InvalidIdentifierException.class, () ->
                IdentifierUtils.assertValid("invalid namespace:path")
        );
    }

    /**
     * Asserts an identifier whose path contains a space. The assertion helper must raise the
     * identifier-specific exception rather than accepting the invalid resource path text.
     */
    @Test
    void testAssertValidThrowsForInvalidPath() {
        assertThrows(InvalidIdentifierException.class, () ->
                IdentifierUtils.assertValid("namespace:invalid path")
        );
    }

    /**
     * Checks the standalone lowercase character-screening set against letters, digits, and
     * separators. It must allow colon and slash while rejecting the tested uppercase letters and
     * invalid punctuation.
     */
    @Test
    void testIsCharValid() {
        // Valid characters
        assertTrue(IdentifierUtils.isCharValid('a'));
        assertTrue(IdentifierUtils.isCharValid('z'));
        assertTrue(IdentifierUtils.isCharValid('0'));
        assertTrue(IdentifierUtils.isCharValid('9'));
        assertTrue(IdentifierUtils.isCharValid('_'));
        assertTrue(IdentifierUtils.isCharValid(':'));
        assertTrue(IdentifierUtils.isCharValid('/'));
        assertTrue(IdentifierUtils.isCharValid('.'));
        assertTrue(IdentifierUtils.isCharValid('-'));

        // Invalid characters
        assertFalse(IdentifierUtils.isCharValid('A'));
        assertFalse(IdentifierUtils.isCharValid('Z'));
        assertFalse(IdentifierUtils.isCharValid(' '));
        assertFalse(IdentifierUtils.isCharValid('@'));
        assertFalse(IdentifierUtils.isCharValid('#'));
    }

    /**
     * Checks complete path strings with accepted punctuation and uppercase letters. Paths
     * containing spaces or at-sign punctuation must fail while the tested valid component strings
     * pass.
     */
    @Test
    void testIsPathValid() {
        assertTrue(IdentifierUtils.isPathValid("valid_path"));
        assertTrue(IdentifierUtils.isPathValid("valid-path"));
        assertTrue(IdentifierUtils.isPathValid("valid.path"));
        assertTrue(IdentifierUtils.isPathValid("ValidPath"));
        assertTrue(IdentifierUtils.isPathValid("path123"));

        assertFalse(IdentifierUtils.isPathValid("invalid path"));
        assertFalse(IdentifierUtils.isPathValid("invalid@path"));
    }

    /**
     * Checks the canonical path character predicate with letters, digits, and punctuation.
     * Uppercase letters must pass, while slash and colon remain rejected despite standalone input
     * screening allowing them.
     */
    @Test
    void testIsPathCharacterValid() {
        // Valid path characters
        assertTrue(IdentifierUtils.isPathCharacterValid('a'));
        assertTrue(IdentifierUtils.isPathCharacterValid('z'));
        assertTrue(IdentifierUtils.isPathCharacterValid('A'));
        assertTrue(IdentifierUtils.isPathCharacterValid('Z'));
        assertTrue(IdentifierUtils.isPathCharacterValid('0'));
        assertTrue(IdentifierUtils.isPathCharacterValid('9'));
        assertTrue(IdentifierUtils.isPathCharacterValid('_'));
        assertTrue(IdentifierUtils.isPathCharacterValid('-'));
        assertTrue(IdentifierUtils.isPathCharacterValid('.'));

        // Invalid path characters
        assertFalse(IdentifierUtils.isPathCharacterValid(' '));
        assertFalse(IdentifierUtils.isPathCharacterValid('@'));
        assertFalse(IdentifierUtils.isPathCharacterValid(':'));
        assertFalse(IdentifierUtils.isPathCharacterValid('/'));
    }

    /**
     * Checks complete namespace strings with accepted letters, digits, and punctuation. The tested
     * valid namespaces must pass, while spaces and at-sign punctuation cause validation failure.
     */
    @Test
    void testIsNamespaceValid() {
        assertTrue(IdentifierUtils.isNamespaceValid("valid_namespace"));
        assertTrue(IdentifierUtils.isNamespaceValid("valid-namespace"));
        assertTrue(IdentifierUtils.isNamespaceValid("valid.namespace"));
        assertTrue(IdentifierUtils.isNamespaceValid("ValidNamespace"));
        assertTrue(IdentifierUtils.isNamespaceValid("namespace123"));
        assertTrue(IdentifierUtils.isNamespaceValid("minecraft"));

        assertFalse(IdentifierUtils.isNamespaceValid("invalid namespace"));
        assertFalse(IdentifierUtils.isNamespaceValid("invalid@namespace"));
    }

    /**
     * Checks the canonical namespace character predicate over accepted ASCII components. Uppercase
     * letters must pass, while slash, colon, spaces, and at-sign punctuation remain rejected.
     */
    @Test
    void testIsNamespaceCharacterValid() {
        // Valid namespace characters
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('a'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('z'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('A'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('Z'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('0'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('9'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('_'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('-'));
        assertTrue(IdentifierUtils.isNamespaceCharacterValid('.'));

        // Invalid namespace characters
        assertFalse(IdentifierUtils.isNamespaceCharacterValid(' '));
        assertFalse(IdentifierUtils.isNamespaceCharacterValid('@'));
        assertFalse(IdentifierUtils.isNamespaceCharacterValid(':'));
        assertFalse(IdentifierUtils.isNamespaceCharacterValid('/'));
    }

    /**
     * Checks the empty path against the character-only validator. Validation must succeed because
     * no path character is present to violate the implementation's accepted character set.
     */
    @Test
    void testEmptyPath() {
        assertTrue(IdentifierUtils.isPathValid(""));
    }

    /**
     * Checks the empty namespace against the character-only validator. Validation must succeed
     * independently of the identifier constructor's separate responsibility to substitute its
     * default namespace.
     */
    @Test
    void testEmptyNamespace() {
        assertTrue(IdentifierUtils.isNamespaceValid(""));
    }
}