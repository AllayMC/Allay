package org.allaymc.server.world.storage.leveldb;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * {@code LevelDBUtilsTest} checks
 * little-endian primitives and temporary-buffer output used by storage
 * codecs.
 */
class LevelDBUtilsTest {

    /**
     * Encodes zero, positive, negative, and mixed-byte integers through the
     * storage helper. The byte arrays must match little-endian order and Java's
     * reference integer encoding.
     */
    @Test
    void testInt2ByteArrayLE() {
        // 0 → all zeros
        assertArrayEquals(new byte[]{0, 0, 0, 0}, LevelDBUtils.int2ByteArrayLE(0));

        // 1 → LE: least significant byte first
        assertArrayEquals(new byte[]{1, 0, 0, 0}, LevelDBUtils.int2ByteArrayLE(1));

        // 256 = 0x00000100 → LE: {0x00, 0x01, 0x00, 0x00}
        assertArrayEquals(new byte[]{0, 1, 0, 0}, LevelDBUtils.int2ByteArrayLE(256));

        // -1 = 0xFFFFFFFF → all 0xFF
        assertArrayEquals(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, LevelDBUtils.int2ByteArrayLE(-1));

        int value = 0x12345678;
        byte[] expected = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
        assertArrayEquals(expected, LevelDBUtils.int2ByteArrayLE(value));
    }

    /**
     * Writes three known bytes through a temporary codec buffer callback. The
     * returned array must preserve their order after the callback completes and
     * buffer ownership returns to the helper.
     */
    @Test
    void testWithByteBufToArray() {
        byte[] result = LevelDBUtils.withByteBufToArray(buf -> {
            buf.writeByte(0x01);
            buf.writeByte(0x02);
            buf.writeByte(0x03);
        });
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result);
    }

    /**
     * Runs a buffer callback that emits no data at all. The helper must return
     * an empty byte array rather than unused capacity from its temporary
     * buffer.
     */
    @Test
    void testWithByteBufToArrayEmpty() {
        byte[] result = LevelDBUtils.withByteBufToArray(buf -> {});
        assertArrayEquals(new byte[0], result);
    }
}
