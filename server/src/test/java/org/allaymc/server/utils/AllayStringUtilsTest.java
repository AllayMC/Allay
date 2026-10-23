package org.allaymc.server.utils;

import org.allaymc.api.utils.AllayStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code AllayStringUtilsTest} verifies delimiter splitting and command argument grouping around
 * quotes and braces.
 *
 * @author daoge_cmd
 */
class AllayStringUtilsTest {
    /**
     * {@code testStr} provides colons and a semicolon for all delimiter tests. Each method uses
     * the same unchanged text to compare split boundaries and retained suffixes.
     */
    static String testStr = "aaa:bbb;ccc:ddd";

    /**
     * Splits shared sample text using single-character delimiters and a bounded result count. The
     * final part must retain the unsplit suffix, and a one-part limit must be rejected.
     */
    @Test
    void testFastSplit() {
        assertEquals(List.of("aaa:bbb", "ccc:ddd"), AllayStringUtils.fastSplit(testStr, ";"));
        assertEquals(List.of("aaa", "bbb;ccc", "ddd"), AllayStringUtils.fastSplit(testStr, ":"));
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), AllayStringUtils.fastSplit(testStr, ":", 2));
        assertThrows(IllegalArgumentException.class, () -> AllayStringUtils.fastSplit(testStr, ":", 1));
    }

    /**
     * Splits shared sample text at its first delimiter and also uses an absent delimiter. The
     * missing delimiter must retain the default first component and original suffix.
     */
    @Test
    void testFastTwoPartSplit() {
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), List.of(AllayStringUtils.fastTwoPartSplit(testStr, ":", "")));
        assertEquals(List.of("", "aaa:bbb;ccc:ddd"), List.of(AllayStringUtils.fastTwoPartSplit(testStr, "?", "")));
    }

    /**
     * Groups commands containing quotes, selector spaces, and nested JSON braces. Results must
     * remove outer grouping quotes while preserving spaces and quotes inside brace-delimited data.
     */
    @Test
    void testSplitCommandArgs() {
        assertEquals(List.of("a", "b", "c"), AllayStringUtils.splitCommandArgs("a b c"));
        assertEquals(List.of("a b", "c"), AllayStringUtils.splitCommandArgs("\"a b\" c"));
        assertEquals(List.of("a", "b c"), AllayStringUtils.splitCommandArgs("a \"b c\""));
        assertEquals(List.of("a", "{b}"), AllayStringUtils.splitCommandArgs("a \"{b}\""));
        assertEquals(List.of("a", "{b} {c}"), AllayStringUtils.splitCommandArgs("a \"{b} {c}\""));
        assertEquals(List.of("a", "@e[name=daoge cmd]"), AllayStringUtils.splitCommandArgs("a @e[name=\"daoge cmd\"]"));
        assertEquals(List.of("@a", "{\"rawtext\":[{\"text\":\"§9Hello everyone!\"}]}"), AllayStringUtils.splitCommandArgs("@a {\"rawtext\":[{\"text\":\"§9Hello everyone!\"}]}"));
    }
}