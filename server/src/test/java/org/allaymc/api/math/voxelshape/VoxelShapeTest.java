package org.allaymc.api.math.voxelshape;

import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
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

    @Test
    void testIsFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs1.isFull(face));
        }

        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4f, 0, 0.4f, 0.6f, 1, 0.6f)
                .build();

        assertFalse(vs2.isFull(BlockFace.UP));
        assertFalse(vs2.isFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4f, 0.4f, 0.4f, 0.6f, 0.6f, 0.6f)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs3.isFull(face));
        }

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0, 0, 0.5f, 1, 1)
                .build();

        assertFalse(vs4.isFull(BlockFace.UP));
        assertFalse(vs4.isFull(BlockFace.DOWN));
        assertFalse(vs4.isFull(BlockFace.EAST));
        assertFalse(vs4.isFull(BlockFace.NORTH));
        assertFalse(vs4.isFull(BlockFace.SOUTH));
        assertTrue(vs4.isFull(BlockFace.WEST));

        var vs5 = VoxelShape
                .builder()
                .build();

        for (var face : BlockFace.values()) {
            assertFalse(vs5.isFull(face));
        }

        var vs6 = VoxelShape
                .builder()
                .vacancy(0, 0, 0, 1, 1, 1)
                .build();

        for (var face : BlockFace.values()) {
            assertFalse(vs6.isFull(face));
        }
    }

    @Test
    void testIsCenterFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs1.isCenterFull(face));
        }

        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.45f, 0, 0.45f, 0.55f, 1, 0.55f)
                .build();

        assertFalse(vs2.isCenterFull(BlockFace.UP));
        assertFalse(vs2.isCenterFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isCenterFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.35f, 0.35f, 0.35f, 0.65f, 0.65f, 0.65f)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs3.isCenterFull(face));
        }

        var vs4 = VoxelShape
                .builder()
                .solid(0.375f, 0, 0.375f, 0.625f, 1, 0.625f)
                .build();

        assertTrue(vs4.isCenterFull(BlockFace.UP));
        assertTrue(vs4.isCenterFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertFalse(vs4.isCenterFull(face));
        }
    }

    @Test
    void testIsFullBlock() {
        // 完整方块
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        assertTrue(vs1.isFullBlock());

        // 不完整方块（有空缺）
        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4f, 0, 0.4f, 0.6f, 1, 0.6f)
                .build();
        assertFalse(vs2.isFullBlock());

        // 部分支撑面不完整
        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.5f, 1)
                .build();
        assertFalse(vs3.isFullBlock());
    }

    @Test
    void testIsEdgeFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        for (var face : BlockFace.values()) {
            assertTrue(vs1.isEdgeFull(face));
        }

        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.0f, 0.0f, 0.0f, 0.125f, 1, 0.125f)
                .build();
        for (var face : BlockFace.getVerticalBlockFaces()) {
            assertFalse(vs2.isEdgeFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 0.125f, 1, 1)
                .build();
        assertTrue(vs3.isEdgeFull(BlockFace.WEST));
        assertFalse(vs3.isEdgeFull(BlockFace.EAST));
        assertFalse(vs3.isEdgeFull(BlockFace.NORTH));
        assertFalse(vs3.isEdgeFull(BlockFace.SOUTH));
        assertFalse(vs3.isEdgeFull(BlockFace.UP));
        assertFalse(vs3.isEdgeFull(BlockFace.DOWN));

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.2f, 0.8f, 0.2f, 0.8f, 1, 0.8f)
                .build();
        assertTrue(vs4.isEdgeFull(BlockFace.UP));

        var vs5 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1f, 0.8f, 0.1f, 0.9f, 1, 0.9f)
                .build();
        assertFalse(vs5.isEdgeFull(BlockFace.UP));
    }

    @Test
    void testIntersectsRay() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        assertTrue(vs1.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1f, 0.1f, 0.1f, 0.9f, 0.9f, 0.9f)
                .build();
        assertTrue(vs2.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4f, 0, 0.4f, 0.6f, 1, 0.6f)
                .build();
        assertFalse(vs3.intersectsRay(0.5f, 0, 0.5f, 0.5f, 1, 0.5f));

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0, 0, 0, 1, 1, 1)
                .build();
        assertFalse(vs4.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs5 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0, 0, 0, 1, 0.4f, 1)
                .vacancy(0, 0.6f, 0, 1, 1, 1)
                .build();
        assertTrue(vs5.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs6 = VoxelShape
                .builder()
                .vacancy(0, 0.4f, 0, 1, 0.6f, 1)
                .solid(0, 0, 0, 1, 0.4f, 1)
                .solid(0, 0.6f, 0, 1, 1, 1)
                .build();
        assertTrue(vs6.intersectsRay(0, 0, 0, 1, 1, 1));
        assertFalse(vs6.intersectsRay(0, 0.4f, 0, 1, 0.4f, 1));
        assertFalse(vs6.intersectsRay(0, 0.41f, 0, 1, 0.41f, 1));
        assertFalse(vs6.intersectsRay(0, 0.59f, 0, 1, 0.59f, 1));
        assertFalse(vs6.intersectsRay(0, 0.6f, 0, 1, 0.6f, 1));

        var vs7 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.9f, 1)
                .solid(0, 0.1f, 0, 1, 1, 1)
                .vacancy(0.4f, 0, 0.4f, 0.6f, 1, 0.6f)
                .build();
        assertFalse(vs7.intersectsRay(0.5f, 0, 0.5f, 0.5f, 1, 0.5f));
    }
}