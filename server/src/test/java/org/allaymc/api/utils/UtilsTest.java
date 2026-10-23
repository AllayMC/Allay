package org.allaymc.api.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code UtilsTest} verifies integer bit widths and ordered server byte-array concatenation.
 *
 * @author daoge_cmd
 */
class UtilsTest {
    /**
     * Checks positive integers on and between successive powers of two. Required widths must
     * increase at each new high bit and remain constant for intermediate values.
     */
    @Test
    void testComputeRequiredBits() {
        assertEquals(1, Utils.computeRequiredBits(1));
        assertEquals(2, Utils.computeRequiredBits(2));
        assertEquals(2, Utils.computeRequiredBits(3));
        assertEquals(3, Utils.computeRequiredBits(4));
        assertEquals(3, Utils.computeRequiredBits(5));
        assertEquals(4, Utils.computeRequiredBits(8));
        assertEquals(4, Utils.computeRequiredBits(9));
        assertEquals(5, Utils.computeRequiredBits(16));
        assertEquals(5, Utils.computeRequiredBits(17));
        assertEquals(6, Utils.computeRequiredBits(32));
        assertEquals(6, Utils.computeRequiredBits(33));
    }

    /**
     * Concatenates three byte arrays through the server helper. The observed result must retain
     * every source array's content in the original argument order.
     */
    @Test
    void testAppendBytes() {
        assertEquals(new String(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), new String(org.allaymc.server.utils.Utils.appendBytes(new byte[]{1, 2, 3}, new byte[]{4, 5, 6}, new byte[]{7, 8, 9})));
    }
}
