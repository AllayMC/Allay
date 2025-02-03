package org.allaymc.api.block.data;

import org.joml.Vector3d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
class BlockFaceTest {
    /**
     * vec:
     * + -- -- -- -- -- -- -- -- -- --z(SOUTH)
     * |       p(0.1, 0.2, 0.3)
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * x(EAST)
     */
    @Test
    void testRotateVector() {
        var vec = new Vector3d(0.1, 0.2, 0.3);
        assertEquals(vec.toString(), BlockFace.EAST.rotateVector(vec).toString());
        assertEquals(new Vector3d(0.2, 0.9, 0.3).toString(), BlockFace.DOWN.rotateVector(vec).toString());
        assertEquals(new Vector3d(0.8, 0.1, 0.3).toString(), BlockFace.UP.rotateVector(vec).toString());
        assertEquals(new Vector3d(0.9, 0.2, 0.7).toString(), BlockFace.WEST.rotateVector(vec).toString());
        assertEquals(new Vector3d(0.7, 0.2, 0.1).toString(), BlockFace.SOUTH.rotateVector(vec).toString());
        assertEquals(new Vector3d(0.3, 0.2, 0.9).toString(), BlockFace.NORTH.rotateVector(vec).toString());
    }
}