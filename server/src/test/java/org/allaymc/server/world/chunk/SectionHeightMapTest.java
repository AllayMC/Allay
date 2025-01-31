package org.allaymc.server.world.chunk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
class SectionHeightMapTest {
    @Test
    void test() {
        var map = new SectionHeightMap();

        // Initial value should be 0
        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                assertEquals(0, map.get(x, z));
            }
        }

        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                for (var value = 1; value < 16; value++) {
                    map.set(x, z, (byte) value);
                    assertEquals(value, map.get(x, z));
                }
            }
        }
    }
}