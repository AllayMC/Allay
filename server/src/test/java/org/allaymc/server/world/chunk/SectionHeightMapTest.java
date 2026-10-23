package org.allaymc.server.world.chunk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code SectionHeightMapTest} checks
 * zero initialization and valid height values throughout packed section
 * storage.
 *
 * @author daoge_cmd
 */
class SectionHeightMapTest {
    /**
     * Checks every column starts at zero and accepts heights one through
     * fifteen. Reading immediately after each assignment must recover the value
     * from the selected nibble.
     */
    @Test
    void test() {
        var map = new SectionHeightMap();

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