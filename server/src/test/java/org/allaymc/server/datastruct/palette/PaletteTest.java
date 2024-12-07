package org.allaymc.server.datastruct.palette;

import org.allaymc.server.datastruct.bitarray.BitArrayVersion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
class PaletteTest {

    @Test
    void test() {
        var obj0 = new Object();
        var palette = new Palette<>(obj0, BitArrayVersion.V0);
        assertEquals(obj0, palette.get(0));

        var obj1 = new Object();
        palette.set(1, obj1);
        assertEquals(obj1, palette.get(1));

        var obj2 = new Object();
        palette.set(2, obj2);
        assertEquals(obj2, palette.get(2));

        // TODO: more tests
    }
}