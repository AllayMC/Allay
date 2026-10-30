package org.allaymc.server.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code UtilsTest} verifies ordered concatenation of several byte arrays through the server
 * helper.
 *
 * @author daoge_cmd
 */
class UtilsTest {
    /**
     * Concatenates three nonempty byte arrays into one result. Its content must preserve the array
     * argument order and each source array's internal byte order.
     */
    @Test
    void testAppendBytes() {
        assertEquals(new String(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), new String(Utils.appendBytes(new byte[]{1, 2, 3}, new byte[]{4, 5, 6}, new byte[]{7, 8, 9})));
    }
}
