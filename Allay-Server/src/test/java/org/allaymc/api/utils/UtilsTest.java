package org.allaymc.api.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/7/7
 *
 * @author daoge_cmd
 */
class UtilsTest {
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

    @Test
    void testAppendBytes() {
        assertEquals(new String(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), new String(Utils.appendBytes(new byte[]{1, 2, 3}, new byte[]{4, 5, 6}, new byte[]{7, 8, 9})));
    }

    @Test
    void testConvertByteBuf2Array() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        assertEquals(new String(bytes), new String(Utils.convertByteBuf2Array(buf)));
    }
}
