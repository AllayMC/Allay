package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.*;

class LevelDBUtilsTest {

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

        // Verify consistency with standard Java LE encoding
        int value = 0x12345678;
        byte[] expected = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
        assertArrayEquals(expected, LevelDBUtils.int2ByteArrayLE(value));
    }

    @Test
    void testWithByteBufToArray() {
        // Write known bytes and verify the output
        byte[] result = LevelDBUtils.withByteBufToArray(buf -> {
            buf.writeByte(0x01);
            buf.writeByte(0x02);
            buf.writeByte(0x03);
        });
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, result);
    }

    @Test
    void testWithByteBufToArrayEmpty() {
        // Empty write should return empty array
        byte[] result = LevelDBUtils.withByteBufToArray(buf -> {});
        assertArrayEquals(new byte[0], result);
    }
}
