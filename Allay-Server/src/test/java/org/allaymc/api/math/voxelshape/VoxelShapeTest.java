package org.allaymc.api.math.voxelshape;

import org.allaymc.api.data.BlockFace;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.5f, 0.5f, 0, 1, 1, 1)
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
        assertFalse(vs.intersectsPoint(new Vector3f(0.5f, 0.5f, 0.5f)));
        assertTrue(vs.intersectsPoint(new Vector3f(0.5f, 0.5f, 0.05f)));

        var aabb = new AABBf(0.2f, 0.2f, 0.2f, 0.8f, 0.8f, 0.8f);
        assertFalse(vs.intersectsAABB(aabb));
    }

    @Test
    void testTranslate() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1f, 0.1f, 0.1f, 0.9f, 0.9f, 0.9f)
                .build();
        validateTranslateResult(vs.translate(1, 1, 1));
        validateTranslateResult(vs.translate(new Vector3f(1, 1, 1)));
    }

    void validateTranslateResult(VoxelShape vs) {
        var solid = vs.getSolids().iterator().next();
        assertEquals(1, solid.minX());
        assertEquals(1, solid.minY());
        assertEquals(1, solid.minZ());
        assertEquals(2, solid.maxX());
        assertEquals(2, solid.maxY());
        assertEquals(2, solid.maxZ());
        var vacancy = vs.getVacancies().iterator().next();
        assertEquals(1.1f, vacancy.minX());
        assertEquals(1.1f, vacancy.minY());
        assertEquals(1.1f, vacancy.minZ());
        assertEquals(1.9f, vacancy.maxX());
        assertEquals(1.9f, vacancy.maxY());
        assertEquals(1.9f, vacancy.maxZ());

    }
}