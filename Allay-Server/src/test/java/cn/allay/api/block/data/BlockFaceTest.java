package cn.allay.api.block.data;

import org.joml.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/9/2
 *
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
        var vec = new Vector3f(0.1f, 0.2f, 0.3f);
        assertEquals(vec.toString(), BlockFace.EAST.rotateVector(vec).toString());
        assertEquals(new Vector3f(0.2f, 0.9f, 0.3f).toString(), BlockFace.DOWN.rotateVector(vec).toString());
        assertEquals(new Vector3f(0.8f, 0.1f, 0.3f).toString(), BlockFace.UP.rotateVector(vec).toString());
        assertEquals(new Vector3f(0.9f, 0.2f, 0.7f).toString(), BlockFace.WEST.rotateVector(vec).toString());
        assertEquals(new Vector3f(0.7f, 0.2f, 0.1f).toString(), BlockFace.SOUTH.rotateVector(vec).toString());
        assertEquals(new Vector3f(0.3f, 0.2f, 0.9f).toString(), BlockFace.NORTH.rotateVector(vec).toString());
    }
}