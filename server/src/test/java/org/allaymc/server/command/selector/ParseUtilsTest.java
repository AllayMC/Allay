package org.allaymc.server.command.selector;

import lombok.SneakyThrows;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.junit.jupiter.api.Test;

import static org.allaymc.server.command.selector.ParseUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ParseUtilsTest} checks selector offset parsing, value constraints,
 * interval bounds, and game-mode aliases.
 *
 * @author daoge_cmd
 */
class ParseUtilsTest {
    /**
     * Combines absolute integer text, a bare tilde, and positive and negative
     * tilde offsets. The assertions protect absolute values ignoring the base
     * and relative values adding to that base.
     */
    @SneakyThrows
    @Test
    void testParseOffsetInt() {
        var base = 1;
        assertEquals(1, parseOffsetInt("1", base));
        assertEquals(1, parseOffsetInt("1", 2));
        assertEquals(1, parseOffsetInt("~", base));
        assertEquals(2, parseOffsetInt("~1", base));
        assertEquals(0, parseOffsetInt("~-1", base));
    }

    /**
     * Combines absolute floating-point text, a bare tilde, and signed tilde
     * offsets. The assertions protect single-precision offset behavior and
     * ensure absolute values do not incorporate the supplied base.
     */
    @SneakyThrows
    @Test
    void testParseOffsetFloat() {
        var base = 1.0f;
        assertEquals(1.0f, parseOffsetFloat("1.0", base));
        assertEquals(1.0f, parseOffsetFloat("1.0", 2.0f));
        assertEquals(1.0f, parseOffsetFloat("~", base));
        assertEquals(2.0f, parseOffsetFloat("~1.0", base));
        assertEquals(0.0f, parseOffsetFloat("~-1.0", base));
    }

    /**
     * Combines absolute double text, a bare tilde, and signed tilde offsets.
     * The assertions protect double-precision offset behavior and ensure
     * absolute values do not incorporate the supplied base.
     */
    @SneakyThrows
    @Test
    void testParseOffsetDouble() {
        var base = 1.0;
        assertEquals(1.0, parseOffsetDouble("1.0", base));
        assertEquals(1.0, parseOffsetDouble("1.0", 2.0));
        assertEquals(1.0, parseOffsetDouble("~", base));
        assertEquals(2.0, parseOffsetDouble("~1.0", base));
        assertEquals(0.0, parseOffsetDouble("~-1.0", base));
    }

    /**
     * Checks values with and without a leading reversal marker. The assertions
     * protect detection from the token prefix so handlers can distinguish an
     * excluded value from an ordinary value.
     */
    @Test
    void testCheckReversed() {
        assertFalse(checkReversed("xxx"));
        assertTrue(checkReversed("!xxx"));
    }

    /**
     * Supplies a reversal-prefixed value to a handler guard that forbids
     * negation. The assertion protects rejection through a selector syntax
     * exception before a handler interprets the remaining text.
     */
    @Test
    void testCannotReversed() {
        assertThrows(SelectorSyntaxException.class, () -> cannotReversed("!xxx"));
    }

    /**
     * Supplies two values for a selector key that permits at most one. The
     * assertion protects the duplicate-occurrence guard and its selector syntax
     * exception for excessive values.
     */
    @Test
    void testSingleArgument() {
        assertThrows(SelectorSyntaxException.class, () -> singleArgument(new String[]{"x1", "x2"}, "testKey"));
    }

    /**
     * Checks ordered and reversed interval endpoints, endpoint equality, and an
     * outside value. The assertions protect inclusive comparisons and identical
     * acceptance regardless of the order in which bounds are supplied.
     */
    @Test
    void testCheckBetween() {
        assertTrue(checkBetween(1, 2, 1.5));
        assertTrue(checkBetween(2, 1, 1.5));
        assertTrue(checkBetween(1, 2, 1));
        assertTrue(checkBetween(2, 1, 2));
        assertFalse(checkBetween(1, 2, 3));
        assertFalse(checkBetween(2, 1, 3));
    }

    /**
     * Checks named, abbreviated, and numeric selector game-mode aliases,
     * followed by an unknown token. The assertions protect selector-facing
     * numeric values, including spectator value six, and rejection of
     * unsupported spellings.
     */
    @SneakyThrows
    @Test
    void testParseGameMode() {
        assertEquals(0, parseGameMode("survival"));
        assertEquals(0, parseGameMode("s"));
        assertEquals(0, parseGameMode("0"));
        assertEquals(1, parseGameMode("creative"));
        assertEquals(1, parseGameMode("c"));
        assertEquals(1, parseGameMode("1"));
        assertEquals(2, parseGameMode("adventure"));
        assertEquals(2, parseGameMode("a"));
        assertEquals(2, parseGameMode("2"));
        assertEquals(6, parseGameMode("spectator"));
        assertEquals(6, parseGameMode("6"));
        assertThrows(SelectorSyntaxException.class, () -> parseGameMode("xxx"));
    }
}