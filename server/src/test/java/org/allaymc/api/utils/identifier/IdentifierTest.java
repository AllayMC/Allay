package org.allaymc.api.utils.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class IdentifierTest {

    @Test
    void testConstructorWithNamespaceAndPath() {
        Identifier id = new Identifier("minecraft", "stone");

        assertEquals("minecraft", id.namespace());
        assertEquals("stone", id.path());
    }

    @Test
    void testConstructorWithStringContainingColon() {
        Identifier id = new Identifier("custom:item");

        assertEquals("custom", id.namespace());
        assertEquals("item", id.path());
    }

    @Test
    void testConstructorWithStringWithoutColon() {
        Identifier id = new Identifier("stone");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    @Test
    void testConstructorWithEmptyNamespace() {
        Identifier id = new Identifier("", "path");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("path", id.path());
    }

    @Test
    void testConstructorWithStringArray() {
        Identifier id = new Identifier(new String[]{"namespace", "path"});

        assertEquals("namespace", id.namespace());
        assertEquals("path", id.path());
    }

    @Test
    void testConstructorWithColonPrefixedString() {
        Identifier id = new Identifier(":path");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("path", id.path());
    }

    @Test
    void testInvalidNamespaceThrowsException() {
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("invalid namespace", "path")
        );
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("invalid@namespace", "path")
        );
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("invalid#namespace", "path")
        );
    }

    @Test
    void testInvalidPathThrowsException() {
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("namespace", "invalid path")
        );
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("namespace", "invalid@path")
        );
        assertThrows(InvalidIdentifierException.class, () ->
                new Identifier("namespace", "invalid#path")
        );
    }

    @Test
    void testValidNamespaceCharacters() {
        assertDoesNotThrow(() -> new Identifier("abc", "path"));
        assertDoesNotThrow(() -> new Identifier("ABC", "path"));
        assertDoesNotThrow(() -> new Identifier("a1b2c3", "path"));
        assertDoesNotThrow(() -> new Identifier("a_b", "path"));
        assertDoesNotThrow(() -> new Identifier("a-b", "path"));
        assertDoesNotThrow(() -> new Identifier("a.b", "path"));
    }

    @Test
    void testValidPathCharacters() {
        assertDoesNotThrow(() -> new Identifier("ns", "abc"));
        assertDoesNotThrow(() -> new Identifier("ns", "ABC"));
        assertDoesNotThrow(() -> new Identifier("ns", "a1b2c3"));
        assertDoesNotThrow(() -> new Identifier("ns", "a_b"));
        assertDoesNotThrow(() -> new Identifier("ns", "a-b"));
        assertDoesNotThrow(() -> new Identifier("ns", "a.b"));
    }

    @Test
    void testToString() {
        Identifier id = new Identifier("minecraft", "stone");

        assertEquals("minecraft:stone", id.toString());
    }

    @Test
    void testToStringWithDefaultNamespace() {
        Identifier id = new Identifier("cake");

        assertEquals("minecraft:cake", id.toString());
    }

    @Test
    void testEquals() {
        Identifier id1 = new Identifier("minecraft", "stone");
        Identifier id2 = new Identifier("minecraft", "stone");
        Identifier id3 = new Identifier("minecraft:stone");
        Identifier id4 = new Identifier("minecraft", "dirt");
        Identifier id5 = new Identifier("custom", "stone");

        assertEquals(id1, id2);
        assertEquals(id1, id3);
        assertNotEquals(id1, id4);
        assertNotEquals(id1, id5);
        assertNotEquals(null, id1);
        assertNotEquals("minecraft:stone", id1);
    }

    @Test
    void testHashCode() {
        Identifier id1 = new Identifier("minecraft", "stone");
        Identifier id2 = new Identifier("minecraft", "stone");
        Identifier id3 = new Identifier("minecraft", "dirt");

        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }

    @Test
    void testClone() {
        Identifier original = new Identifier("minecraft", "stone");
        Identifier cloned = original.clone();

        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testDefaultNamespaceConstant() {
        assertEquals("minecraft", Identifier.DEFAULT_NAMESPACE);
    }

    @Test
    void testNamespaceSeparatorConstant() {
        assertEquals(":", Identifier.NAMESPACE_SEPARATOR);
    }

    @Test
    void testComplexPath() {
        Identifier id = new Identifier("minecraft", "blocks.stone_brick");

        assertEquals("minecraft", id.namespace());
        assertEquals("blocks.stone_brick", id.path());
        assertEquals("minecraft:blocks.stone_brick", id.toString());
    }
}
