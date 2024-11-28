package org.allaymc.server.utils;

import org.allaymc.api.utils.AllayStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author daoge_cmd
 */
class AllayStringUtilsTest {
    static String testStr = "aaa:bbb;ccc:ddd";

    @Test
    void testFastSplit() {
        assertEquals(List.of("aaa:bbb", "ccc:ddd"), AllayStringUtils.fastSplit(testStr, ";"));
        assertEquals(List.of("aaa", "bbb;ccc", "ddd"), AllayStringUtils.fastSplit(testStr, ":"));
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), AllayStringUtils.fastSplit(testStr, ":", 2));
        assertThrows(IllegalArgumentException.class, () -> AllayStringUtils.fastSplit(testStr, ":", 1));
    }

    @Test
    void testFastTwoPartSplit() {
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), List.of(AllayStringUtils.fastTwoPartSplit(testStr, ":", "")));
        assertEquals(List.of("", "aaa:bbb;ccc:ddd"), List.of(AllayStringUtils.fastTwoPartSplit(testStr, "?", "")));
    }
}