package org.allaymc.api.utils.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class IdentifierUtilsTest {

    static final String idStr = "pnx:love";
    static final String idStr2 = "pnx;love";

    @Test
    void testSplitOn() {
        var id1 = IdentifierUtils.splitOn(idStr, ":");
        var id2 = IdentifierUtils.splitOn(idStr2, ";");
        assertEquals(id1, id2);
    }

    @Test
    void testSplitOnWithoutDelimiter() {
        var id = IdentifierUtils.splitOn("stone", ":");
        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    @Test
    void testTryParse() {
        var id = IdentifierUtils.tryParse(idStr);
        assertNotNull(id);
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void testTryParseWithDefaultNamespace() {
        var id = IdentifierUtils.tryParse("stone");
        assertNotNull(id);
        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    @Test
    void testTryParseInvalidReturnsNull() {
        assertNull(IdentifierUtils.tryParse("invalid namespace:path"));
        assertNull(IdentifierUtils.tryParse("namespace:invalid path"));
        assertNull(IdentifierUtils.tryParse("invalid@:path"));
    }

    @Test
    void testOf() {
        var id = IdentifierUtils.of("pnx", "love");
        assertNotNull(id);
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void testOfInvalidReturnsNull() {
        assertNull(IdentifierUtils.of("invalid namespace", "path"));
        assertNull(IdentifierUtils.of("namespace", "invalid path"));
        assertNull(IdentifierUtils.of("invalid@", "path"));
    }

    @Test
    void testIsValid() {
        assertTrue(IdentifierUtils.isValid(idStr));
        assertFalse(IdentifierUtils.isValid(idStr2));
    }

    @Test
    void testIsValidWithDefaultNamespace() {
        assertTrue(IdentifierUtils.isValid("stone"));
        assertTrue(IdentifierUtils.isValid(":stone"));
    }

    @Test
    void testIsValidWithInvalidCharacters() {
        assertFalse(IdentifierUtils.isValid("ns:path with space"));
        assertFalse(IdentifierUtils.isValid("ns@invalid:path"));
        assertFalse(IdentifierUtils.isValid("ns:path@invalid"));
    }

    @Test
    void testAssertValid() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(idStr));
        assertThrows(InvalidIdentifierException.class, () -> IdentifierUtils.assertValid(idStr2));
    }

    @Test
    void testAssertValidWithDefaultNamespace() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid("stone"));
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(":stone"));
    }

    @Test
    void testAssertValidThrowsForInvalidNamespace() {
        assertThrows(InvalidIdentifierException.class, () ->
                IdentifierUtils.assertValid("invalid namespace:path")
        );
    }

    @Test
    void testAssertValidThrowsForInvalidPath() {
        assertThrows(InvalidIdentifierException.class, () ->
                IdentifierUtils.assertValid("namespace:invalid path")
        );
    }

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

    @Test
    void testEmptyPath() {
        assertTrue(IdentifierUtils.isPathValid(""));
    }

    @Test
    void testEmptyNamespace() {
        assertTrue(IdentifierUtils.isNamespaceValid(""));
    }
}