package org.allaymc.server.world.chunk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code ChunkBitMapTest} checks
 * initial, set, and cleared values across every column flag.
 *
 * @author daoge_cmd
 */
class ChunkBitMapTest {
    /**
     * Traverses every column through its initial {@code false} value, a {@code true}
     * assignment, and a {@code false} assignment. Immediate reads must agree with each
     * update across the complete map.
     */
    @Test
    void test() {
        var map = new ChunkBitMap();
        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                assertFalse(map.get(x, z));
            }
        }

        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                map.set(x, z, true);
                assertTrue(map.get(x, z));
            }
        }

        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                map.set(x, z, false);
                assertFalse(map.get(x, z));
            }
        }
    }
}