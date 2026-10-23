package org.allaymc.api.utils.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code IdentifierTest} verifies identifier defaulting, accepted characters, equality, hashing,
 * and immutable-value cloning.
 *
 * @author daoge_cmd
 */
class IdentifierTest {

    /**
     * Constructs an identifier from explicit namespace and path components. Both record accessors
     * must retain the supplied component text instead of applying an unnecessary default
     * namespace.
     */
    @Test
    void testConstructorWithNamespaceAndPath() {
        Identifier id = new Identifier("minecraft", "stone");

        assertEquals("minecraft", id.namespace());
        assertEquals("stone", id.path());
    }

    /**
     * Constructs an identifier from a string with an explicit namespace separator. The resulting
     * components must split around that separator without changing their retained text.
     */
    @Test
    void testConstructorWithStringContainingColon() {
        Identifier id = new Identifier("custom:item");

        assertEquals("custom", id.namespace());
        assertEquals("item", id.path());
    }

    /**
     * Constructs an identifier from an unqualified path string. The namespace must use the public
     * default while the path remains the supplied resource name.
     */
    @Test
    void testConstructorWithStringWithoutColon() {
        Identifier id = new Identifier("stone");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("stone", id.path());
    }

    /**
     * Constructs an identifier with an empty explicit namespace. Construction must substitute the
     * public default namespace while retaining the supplied path as the resource component.
     */
    @Test
    void testConstructorWithEmptyNamespace() {
        Identifier id = new Identifier("", "path");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("path", id.path());
    }

    /**
     * Constructs an identifier from a two-element component array. Namespace and path accessors
     * must retain the corresponding array elements in their original component roles.
     */
    @Test
    void testConstructorWithStringArray() {
        Identifier id = new Identifier(new String[]{"namespace", "path"});

        assertEquals("namespace", id.namespace());
        assertEquals("path", id.path());
    }

    /**
     * Constructs an identifier from a string beginning with the namespace separator. The empty
     * namespace must default while the suffix becomes the retained resource path.
     */
    @Test
    void testConstructorWithColonPrefixedString() {
        Identifier id = new Identifier(":path");

        assertEquals(Identifier.DEFAULT_NAMESPACE, id.namespace());
        assertEquals("path", id.path());
    }

    /**
     * Attempts construction with spaces and punctuation outside the namespace grammar. Each tested
     * invalid namespace must raise the identifier-specific exception instead of producing a usable
     * record.
     */
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

    /**
     * Attempts construction with spaces and punctuation outside the path grammar. Each tested
     * invalid path must raise the identifier-specific exception instead of producing a usable
     * record.
     */
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

    /**
     * Constructs identifiers using lowercase, uppercase, digits, underscore, hyphen, and period in
     * namespaces. Every tested namespace must pass the implementation's current character
     * validation without rejection.
     */
    @Test
    void testValidNamespaceCharacters() {
        assertDoesNotThrow(() -> new Identifier("abc", "path"));
        assertDoesNotThrow(() -> new Identifier("ABC", "path"));
        assertDoesNotThrow(() -> new Identifier("a1b2c3", "path"));
        assertDoesNotThrow(() -> new Identifier("a_b", "path"));
        assertDoesNotThrow(() -> new Identifier("a-b", "path"));
        assertDoesNotThrow(() -> new Identifier("a.b", "path"));
    }

    /**
     * Constructs identifiers using lowercase, uppercase, digits, underscore, hyphen, and period in
     * paths. Every tested path must pass the implementation's current character validation without
     * rejection.
     */
    @Test
    void testValidPathCharacters() {
        assertDoesNotThrow(() -> new Identifier("ns", "abc"));
        assertDoesNotThrow(() -> new Identifier("ns", "ABC"));
        assertDoesNotThrow(() -> new Identifier("ns", "a1b2c3"));
        assertDoesNotThrow(() -> new Identifier("ns", "a_b"));
        assertDoesNotThrow(() -> new Identifier("ns", "a-b"));
        assertDoesNotThrow(() -> new Identifier("ns", "a.b"));
    }

    /**
     * Renders an identifier with an explicit namespace and path. The output must join both
     * components with the namespace separator into the expected canonical string.
     */
    @Test
    void testToString() {
        Identifier id = new Identifier("minecraft", "stone");

        assertEquals("minecraft:stone", id.toString());
    }

    /**
     * Renders an identifier created from an unqualified path. The output must include the resolved
     * default namespace rather than reproducing only the originally supplied path.
     */
    @Test
    void testToStringWithDefaultNamespace() {
        Identifier id = new Identifier("cake");

        assertEquals("minecraft:cake", id.toString());
    }

    /**
     * Compares identifiers built through separate constructors and values with different
     * components. Equality must follow both retained components and reject {@code null} or unrelated
     * string objects.
     */
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

    /**
     * Compares hash codes of equal identifiers and a representative different path. Equal records
     * must agree, and the tested distinct path must affect the computed hash value.
     */
    @Test
    void testHashCode() {
        Identifier id1 = new Identifier("minecraft", "stone");
        Identifier id2 = new Identifier("minecraft", "stone");
        Identifier id3 = new Identifier("minecraft", "dirt");

        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }

    /**
     * Clones an immutable identifier value through its explicit clone operation. The result must
     * compare equal to the original while remaining a separately allocated identifier instance.
     */
    @Test
    void testClone() {
        Identifier original = new Identifier("minecraft", "stone");
        Identifier cloned = original.clone();

        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    /**
     * Checks the namespace used for unqualified identifiers and empty namespace components. The
     * public default constant must remain the expected Minecraft namespace used by construction
     * tests.
     */
    @Test
    void testDefaultNamespaceConstant() {
        assertEquals("minecraft", Identifier.DEFAULT_NAMESPACE);
    }

    /**
     * Checks the separator used between namespace and path components. The public constant must
     * remain a colon so parsing and string rendering share the expected representation.
     */
    @Test
    void testNamespaceSeparatorConstant() {
        assertEquals(":", Identifier.NAMESPACE_SEPARATOR);
    }

    /**
     * Constructs an identifier whose path combines a period and underscore. Accessors and string
     * rendering must retain that complete path without treating its punctuation as component
     * separators.
     */
    @Test
    void testComplexPath() {
        Identifier id = new Identifier("minecraft", "blocks.stone_brick");

        assertEquals("minecraft", id.namespace());
        assertEquals("blocks.stone_brick", id.path());
        assertEquals("minecraft:blocks.stone_brick", id.toString());
    }
}
