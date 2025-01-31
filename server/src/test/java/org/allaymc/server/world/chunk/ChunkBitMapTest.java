package org.allaymc.server.world.chunk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
class ChunkBitMapTest {
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