package org.allaymc.server.datastruct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ChunkSectionNibbleArrayTest} checks coordinate access, storage-size validation,
 * and transitions between explicit and implicit light-section storage.
 * @author daoge_cmd
 */
class ChunkSectionNibbleArrayTest {
    /**
     * Writes a nonzero light value into supplied and lazily allocated backing storage.
     * Both coordinate-based reads must recover the value at the selected section position.
     */
    @Test
    void testSetAndGet() {
        var array = new ChunkSectionNibbleArray(new byte[2048]);
        array.set(1, 2, 3, 15);
        assertEquals(15, array.get(1, 2, 3));

        array = new ChunkSectionNibbleArray();
        array.set(1, 2, 7, 15);
        assertEquals(15, array.get(1, 2, 7));
    }

    /**
     * Supplies a byte array longer than one chunk section permits. Construction must throw
     * {@code IllegalArgumentException}, protecting the fixed storage size expected by
     * nibble indexing.
     */
    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ChunkSectionNibbleArray(new byte[ChunkSectionNibbleArray.BYTES_LENGTH + 1]));
    }

    /**
     * Changes a zero section to mixed values, then fills every position with fifteen.
     * Storage must allocate for mixed content, disappear at the full-light checksum, and
     * allocate again after a later change.
     */
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
