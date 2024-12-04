package org.allaymc.server.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author daoge_cmd
 */
class AllayRandomTest {
    @Test
    void test() {
        var r1 = new AllayRandom(114514);
        var r2 = new AllayRandom(114514);
        var r3 = new AllayRandom(1919810);

        assertEquals(r1.nextInt(), r2.nextInt());
        assertEquals(r1.nextFloat(), r2.nextFloat());
        assertEquals(r1.nextDouble(), r2.nextDouble());
        assertEquals(r1.nextBoolean(), r2.nextBoolean());
        assertEquals(r1.nextLong(), r2.nextLong());

        assertNotEquals(r1.nextInt(), r3.nextInt());
    }
}