package cn.allay.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringUtilsTest {

    static String testStr = "aaa:bbb;ccc:ddd";

    @Test
    void fastSplit() {
        assertEquals(List.of("aaa:bbb", "ccc:ddd"), StringUtils.fastSplit(testStr, ";"));
        assertEquals(List.of("aaa", "bbb;ccc", "ddd"), StringUtils.fastSplit(testStr, ":"));
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), StringUtils.fastSplit(testStr, ":", 2));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.fastSplit(testStr, ":", 1));
    }

    @Test
    void fastTwoPartSplit() {
        assertEquals(List.of("aaa", "bbb;ccc:ddd"), List.of(StringUtils.fastTwoPartSplit(testStr, ":", "")));
        assertEquals(List.of("", "aaa:bbb;ccc:ddd"), List.of(StringUtils.fastTwoPartSplit(testStr, "?", "")));
    }
}