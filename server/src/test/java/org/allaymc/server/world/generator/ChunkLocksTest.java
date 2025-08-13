package org.allaymc.server.world.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
class ChunkLocksTest {
    @Test
    void test() {
        var locks = new ChunkLocks();
        assertFalse(locks.isLocked(0, 0));
        assertTrue(locks.lock(0, 0));
        assertTrue(locks.isLocked(0, 0));
        assertFalse(locks.lock(0, 0));
        locks.unlock(0, 0);
        assertFalse(locks.isLocked(0, 0));

        assertTrue(locks.lock(0, 0, 1));
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                assertTrue(locks.isLocked(i, j));
                assertFalse(locks.lock(i, j));
            }
        }
        locks.unlock(0, 0, 1);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                assertFalse(locks.isLocked(i, j));
            }
        }
    }
}