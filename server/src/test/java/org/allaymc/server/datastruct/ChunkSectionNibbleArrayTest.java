package org.allaymc.server.datastruct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class ChunkSectionNibbleArrayTest {
    @Test
    void testSetAndGet() {
        var array = new ChunkSectionNibbleArray(new byte[2048]);
        array.set(1, 2, 3, 15);
        assertEquals(15, array.get(1, 2, 3));
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ChunkSectionNibbleArray(new byte[ChunkSectionNibbleArray.BYTES_LENGTH + 1]));
    }

    @Test
    void testDynamicByteArray() {
        var array = new ChunkSectionNibbleArray();
        assertNull(array.getByteArray());
        array.set(0, 0, 0, 1);
        assertNotNull(array.getByteArray());
        assertEquals(1, array.get(0, 0, 0));

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    array.set(x, y, z, 15);
                }
            }
        }
        assertNull(array.getByteArray());
        assertEquals(15, array.get(0, 0, 0));

        array.set(0, 0, 0, 0);
        assertNotNull(array.getByteArray());
    }
}