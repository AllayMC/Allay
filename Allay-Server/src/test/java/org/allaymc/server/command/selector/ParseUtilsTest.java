package org.allaymc.server.command.selector;

import lombok.SneakyThrows;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.allaymc.server.command.selector.ParseUtils.*;

/**
 * Allay Project 2024/5/18
 *
 * @author daoge_cmd
 */
class ParseUtilsTest {

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

    @Test
    void testCheckReversed() {
        assertFalse(checkReversed("xxx"));
        assertTrue(checkReversed("!xxx"));
    }

    @Test
    void testCannotReversed() {
        assertThrows(SelectorSyntaxException.class, () -> cannotReversed("!xxx"));
    }

    @Test
    void testSingleArgument() {
        assertThrows(SelectorSyntaxException.class, () -> singleArgument(new String[]{"x1", "x2"}, "testKey"));
    }

    @Test
    void testCheckBetween() {
        assertTrue(checkBetween(1, 2, 1.5));
        assertTrue(checkBetween(2, 1, 1.5));
        assertTrue(checkBetween(1, 2, 1));
        assertTrue(checkBetween(2, 1, 2));
        assertFalse(checkBetween(1, 2, 3));
        assertFalse(checkBetween(2, 1, 3));
    }

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