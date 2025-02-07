package org.allaymc.api.math.voxelshape;

import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
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
                .vacancy(0.5, 0.5, 0, 1, 1, 1)
                .build();
        assertFalse(vs.intersectsPoint(0.75, 0.75, 0.5));
        var aabb = new AABBd(0.51, 0.51, 0.01, 0.99, 0.99, 0.99);
        assertFalse(vs.intersectsAABB(aabb));

        var vs2 = vs.rotate(BlockFace.SOUTH);
        assertFalse(vs2.intersectsPoint(0.5, 0.75, 0.75));

        var vs3 = vs.rotate(BlockFace.NORTH);
        assertFalse(vs3.intersectsPoint(0.5, 0.75, 0.25));
    }

    @Test
    void testCauldronShape() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1, 0.1, 0.1, 0.9, 0.9, 0.9)
                .build();

        assertFalse(vs.intersectsPoint(0.5, 0.5, 0.5));
        assertTrue(vs.intersectsPoint(0.5, 0.5, 0.05));
        assertFalse(vs.intersectsPoint(new Vector3d(0.5, 0.5, 0.5)));
        assertTrue(vs.intersectsPoint(new Vector3d(0.5, 0.5, 0.05)));

        var aabb = new AABBd(0.2, 0.2, 0.2, 0.8, 0.8, 0.8);
        assertFalse(vs.intersectsAABB(aabb));
    }

    @Test
    void testTranslate() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1, 0.1, 0.1, 0.9, 0.9, 0.9)
                .build();
        validateTranslateResult(vs.translate(1, 1, 1));
        validateTranslateResult(vs.translate(new Vector3d(1, 1, 1)));
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
        assertEquals(1.1, vacancy.minX());
        assertEquals(1.1, vacancy.minY());
        assertEquals(1.1, vacancy.minZ());
        assertEquals(1.9, vacancy.maxX());
        assertEquals(1.9, vacancy.maxY());
        assertEquals(1.9, vacancy.maxZ());
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
                .vacancy(0.4, 0, 0.4, 0.6, 1, 0.6)
                .build();

        assertFalse(vs2.isFull(BlockFace.UP));
        assertFalse(vs2.isFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4, 0.4, 0.4, 0.6, 0.6, 0.6)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs3.isFull(face));
        }

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0, 0, 0.5, 1, 1)
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
                .vacancy(0.45, 0, 0.45, 0.55, 1, 0.55)
                .build();

        assertFalse(vs2.isCenterFull(BlockFace.UP));
        assertFalse(vs2.isCenterFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isCenterFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.35, 0.35, 0.35, 0.65, 0.65, 0.65)
                .build();

        for (var face : BlockFace.values()) {
            assertTrue(vs3.isCenterFull(face));
        }

        var vs4 = VoxelShape
                .builder()
                .solid(0.375, 0, 0.375, 0.625, 1, 0.625)
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
                .vacancy(0.4, 0, 0.4, 0.6, 1, 0.6)
                .build();
        assertFalse(vs2.isFullBlock());

        // 部分支撑面不完整
        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.5, 1)
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
                .vacancy(0.0, 0.0, 0.0, 0.125, 1, 0.125)
                .build();
        for (var face : BlockFace.getVerticalBlockFaces()) {
            assertFalse(vs2.isEdgeFull(face));
        }

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 0.125, 1, 1)
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
                .vacancy(0.2, 0.8, 0.2, 0.8, 1, 0.8)
                .build();
        assertTrue(vs4.isEdgeFull(BlockFace.UP));

        var vs5 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1, 0.8, 0.1, 0.9, 1, 0.9)
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
                .vacancy(0.1, 0.1, 0.1, 0.9, 0.9, 0.9)
                .build();
        assertTrue(vs2.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.4, 0, 0.4, 0.6, 1, 0.6)
                .build();
        assertFalse(vs3.intersectsRay(0.5, 0, 0.5, 0, 1, 0));

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0, 0, 0, 1, 1, 1)
                .build();
        assertFalse(vs4.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs5 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0, 0, 0, 1, 0.4, 1)
                .vacancy(0, 0.6, 0, 1, 1, 1)
                .build();
        assertTrue(vs5.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs6 = VoxelShape
                .builder()
                .vacancy(0, 0.4, 0, 1, 0.6, 1)
                .solid(0, 0, 0, 1, 0.4, 1)
                .solid(0, 0.6, 0, 1, 1, 1)
                .build();
        assertTrue(vs6.intersectsRay(0, 0, 0, 1, 1, 1));
        assertFalse(vs6.intersectsRay(0, 0.4, 0, 1, 0, 1));
        assertFalse(vs6.intersectsRay(0, 0.41, 0, 1, 0, 1));
        assertFalse(vs6.intersectsRay(0, 0.59, 0, 1, 0, 1));
        assertFalse(vs6.intersectsRay(0, 0.6, 0, 1, 0, 1));

        var vs7 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.9, 1)
                .solid(0, 0.1, 0, 1, 1, 1)
                .vacancy(0.4, 0, 0.4, 0.6, 1, 0.6)
                .build();
        assertFalse(vs7.intersectsRay(0.5, 0, 0.5, 0, 1, 0));
    }

    @Test
    void testIntersectsRayWithResult() {
        var result = new Vector2d();

        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        assertTrue(vs1.intersectsRay(0, 0, 0, 1, 1, 1, result));
        assertEquals(0, result.x);
        assertEquals(1, result.y);

        var vs2 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0.1, 0.1, 0.1, 0.9, 0.9, 0.9)
                .build();
        assertTrue(vs2.intersectsRay(0, 0, 0, 1, 1, 1, result));
        assertEquals(0, result.x);
        assertEquals(1, result.y);

        result.set(0, 0);
        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .vacancy(0, 0, 0, 1, 1, 1)
                .build();
        assertFalse(vs3.intersectsRay(0, 0, 0, 1, 1, 1, result));
        assertEquals(0, result.x);
        assertEquals(0, result.y);

        var vs4 = VoxelShape
                .builder()
                .solid(0, 0.2, 0, 1, 0.8, 1)
                .vacancy(0, 0.4, 0, 1, 0.6, 1)
                .build();
        assertTrue(vs4.intersectsRay(0, 0, 0, 0, 1, 0, result));
        assertEquals(0.2, result.x);
        assertEquals(0.8, result.y);
    }
}