package org.allaymc.server.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
class UtilsTest {
    @Test
    void testAppendBytes() {
        assertEquals(new String(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), new String(Utils.appendBytes(new byte[]{1, 2, 3}, new byte[]{4, 5, 6}, new byte[]{7, 8, 9})));
    }
}
