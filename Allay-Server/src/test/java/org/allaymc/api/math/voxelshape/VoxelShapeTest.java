package org.allaymc.api.math.voxelshape;

import org.allaymc.api.block.data.BlockFace;
import org.joml.primitives.AABBf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
class VoxelShapeTest {
    @Test
    void testStairShape() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1 ,1)
                .vacancy(0.5f, 0.5f, 0, 1, 1 ,1)
                .build();
        assertFalse(vs.intersectsPoint(0.75f, 0.75f, 0.5f));
        var aabb = new AABBf(0.51f, 0.51f, 0.01f, 0.99f, 0.99f, 0.99f);
        assertFalse(vs.intersectsAABB(aabb));

        var vs2 = vs.rotate(BlockFace.SOUTH);
        assertFalse(vs2.intersectsPoint(0.5f, 0.75f, 0.75f));

        var vs3 = vs.rotate(BlockFace.NORTH);
        assertFalse(vs3.intersectsPoint(0.5f, 0.75f, 0.25f));
    }

    @Test
    void testCauldronShape() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1f, 0.1f, 0.1f, 0.9f, 0.9f, 0.9f)
                .build();

        assertFalse(vs.intersectsPoint(0.5f, 0.5f, 0.5f));
        assertTrue(vs.intersectsPoint(0.5f, 0.5f, 0.05f));

        var aabb = new AABBf(0.2f, 0.2f, 0.2f, 0.8f, 0.8f, 0.8f);
        assertFalse(vs.intersectsAABB(aabb));
    }
}