package org.allaymc.api.math.voxelshape;

import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code VoxelShapeTest} checks composite block geometry, face-support coverage, and
 * ray-hit spans using solid-box fixtures.
 * @author daoge_cmd
 */
class VoxelShapeTest {
    /**
     * Builds a two-part stair and rotates it toward horizontal faces. Empty upper regions
     * must remain outside the composite geometry before and after rotation.
     */
    @Test
    void testStairShape() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.5, 1)
                .solid(0, 0.5, 0, 0.5, 1, 1)
                .build();
        assertFalse(vs.intersectsPoint(0.75, 0.75, 0.5));
        var aabb = new AABBd(0.51, 0.51, 0.01, 0.99, 0.99, 0.99);
        assertFalse(vs.intersectsAABB(aabb));

        var vs2 = vs.rotate(BlockFace.SOUTH);
        assertFalse(vs2.intersectsPoint(0.5, 0.75, 0.75));

        var vs3 = vs.rotate(BlockFace.NORTH);
        assertFalse(vs3.intersectsPoint(0.5, 0.75, 0.25));
    }

    /**
     * Builds a hollow box-like shape from separate walls and caps. Interior points and
     * boxes must miss, while points inside a wall must intersect the corresponding solid.
     */
    @Test
    void testCauldronShape() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.1, 1)
                .solid(0, 0.1, 0, 1, 0.9, 0.1)
                .solid(0, 0.1, 0.9, 1, 0.9, 1)
                .solid(0, 0.1, 0.1, 0.1, 0.9, 0.9)
                .solid(0.9, 0.1, 0.1, 1, 0.9, 0.9)
                .solid(0, 0.9, 0, 1, 1, 1)
                .build();

        assertFalse(vs.intersectsPoint(0.5, 0.5, 0.5));
        assertTrue(vs.intersectsPoint(0.5, 0.5, 0.05));
        assertFalse(vs.intersectsPoint(new Vector3d(0.5, 0.5, 0.5)));
        assertTrue(vs.intersectsPoint(new Vector3d(0.5, 0.5, 0.05)));

        var aabb = new AABBd(0.2, 0.2, 0.2, 0.8, 0.8, 0.8);
        assertFalse(vs.intersectsAABB(aabb));
    }

    /**
     * Translates the same multi-solid shape through scalar and vector overloads. Both
     * translated unions must move their minimum and maximum coordinates by the supplied
     * offset.
     */
    @Test
    void testTranslate() {
        var vs = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.1, 1)
                .solid(0, 0.1, 0, 1, 0.9, 0.1)
                .solid(0, 0.1, 0.9, 1, 0.9, 1)
                .solid(0, 0.1, 0.1, 0.1, 0.9, 0.9)
                .solid(0.9, 0.1, 0.1, 1, 0.9, 0.9)
                .solid(0, 0.9, 0, 1, 1, 1)
                .build();
        validateTranslateResult(vs.translate(1, 1, 1).unionAABB());
        validateTranslateResult(vs.translate(new Vector3d(1, 1, 1)).unionAABB());
    }

    /**
     * Checks that translated bounds move both corners by the same offset.
     */
    void validateTranslateResult(AABBdc solid) {
        assertEquals(1, solid.minX());
        assertEquals(1, solid.minY());
        assertEquals(1, solid.minZ());
        assertEquals(2, solid.maxX());
        assertEquals(2, solid.maxY());
        assertEquals(2, solid.maxZ());
    }

    /**
     * Checks solid, perforated, hollow, partial, and empty face-support fixtures. Fullness
     * must combine aligned rectangle coverage and distinguish interior holes from gaps on
     * a requested exterior face.
     */
    @Test
    void testIsFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();

        for (var face : BlockFace.VALUES) {
            assertTrue(vs1.isFull(face));
        }

        var vs2 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 0.4, 1.0, 1.0)
                .solid(0.6, 0.0, 0.0, 1.0, 1.0, 1.0)
                .solid(0.4, 0.0, 0.0, 0.6, 1.0, 0.4)
                .solid(0.4, 0.0, 0.6, 0.6, 1.0, 1.0)
                .build();


        assertFalse(vs2.isFull(BlockFace.UP));
        assertFalse(vs2.isFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isFull(face));
        }

        var vs3 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 1.0, 0.4, 1.0)
                .solid(0.0, 0.6, 0.0, 1.0, 1.0, 1.0)
                .solid(0.0, 0.4, 0.0, 0.4, 0.6, 1.0)
                .solid(0.6, 0.4, 0.0, 1.0, 0.6, 1.0)
                .solid(0.4, 0.4, 0.0, 0.6, 0.6, 0.4)
                .solid(0.4, 0.4, 0.6, 0.6, 0.6, 1.0)
                .build();


        for (var face : BlockFace.VALUES) {
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

        for (var face : BlockFace.VALUES) {
            assertFalse(vs5.isFull(face));
        }

        var vs6 = VoxelShape
                .builder()
                .build();

        for (var face : BlockFace.VALUES) {
            assertFalse(vs6.isFull(face));
        }
    }

    /**
     * Checks central support using full, perforated, hollow, and narrow-column fixtures.
     * Only the projected central square must be covered, and unsupported side faces must
     * remain distinguishable.
     */
    @Test
    void testIsCenterFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();

        for (var face : BlockFace.VALUES) {
            assertTrue(vs1.isCenterFull(face));
        }

        var vs2 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 0.45, 1.0, 1.0)
                .solid(0.55, 0.0, 0.0, 1.0, 1.0, 1.0)
                .solid(0.45, 0.0, 0.0, 0.55, 1.0, 0.45)
                .solid(0.45, 0.0, 0.55, 0.55, 1.0, 1.0)
                .build();

        assertFalse(vs2.isCenterFull(BlockFace.UP));
        assertFalse(vs2.isCenterFull(BlockFace.DOWN));
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            assertTrue(vs2.isCenterFull(face));
        }

        var vs3 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 1.0, 0.35, 1.0)
                .solid(0.0, 0.65, 0.0, 1.0, 1.0, 1.0)
                .solid(0.0, 0.35, 0.0, 0.35, 0.65, 1.0)
                .solid(0.65, 0.35, 0.0, 1.0, 0.65, 1.0)
                .solid(0.35, 0.35, 0.0, 0.65, 0.65, 0.35)
                .solid(0.35, 0.35, 0.65, 0.65, 0.65, 1.0)
                .build();


        for (var face : BlockFace.VALUES) {
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

    /**
     * Checks a complete block, a block with a through-gap, and a half-height block.
     * Whole-block support must require full coverage on every exterior face.
     */
    @Test
    void testIsFullBlock() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        assertTrue(vs1.isFullBlock());

        var vs2 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 0.4, 1.0, 1.0)
                .solid(0.6, 0.0, 0.0, 1.0, 1.0, 1.0)
                .solid(0.4, 0.0, 0.0, 0.6, 1.0, 0.4)
                .solid(0.4, 0.0, 0.6, 0.6, 1.0, 1.0)
                .build();

        assertFalse(vs2.isFullBlock());

        var vs3 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 0.5, 1)
                .build();
        assertFalse(vs3.isFullBlock());
    }

    /**
     * Checks full blocks and shapes missing or retaining different perimeter strips. Edge
     * support must require the complete requested perimeter while allowing an uncovered
     * face center.
     */
    @Test
    void testIsEdgeFull() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        for (var face : BlockFace.VALUES) {
            assertTrue(vs1.isEdgeFull(face));
        }

        var vs2 = VoxelShape.builder()
                .solid(0.125, 0.0, 0.0, 1.0, 1.0, 1.0)
                .solid(0.0, 0.0, 0.125, 0.125, 1.0, 1.0)
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

        var vs4 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 1.0, 0.8, 1.0)
                .solid(0.0, 0.8, 0.0, 0.2, 1.0, 1.0)
                .solid(0.8, 0.8, 0.0, 1.0, 1.0, 1.0)
                .solid(0.2, 0.8, 0.0, 0.8, 1.0, 0.2)
                .solid(0.2, 0.8, 0.8, 0.8, 1.0, 1.0)
                .build();

        assertTrue(vs4.isEdgeFull(BlockFace.UP));

        var vs5 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 1.0, 0.8, 1.0)
                .solid(0.0, 0.8, 0.0, 0.1, 1.0, 1.0)
                .solid(0.9, 0.8, 0.0, 1.0, 1.0, 1.0)
                .solid(0.1, 0.8, 0.0, 0.9, 1.0, 0.1)
                .solid(0.1, 0.8, 0.9, 0.9, 1.0, 1.0)
                .build();

        assertFalse(vs5.isEdgeFull(BlockFace.UP));
    }

    /**
     * Checks rays against full, hollow, perforated, and slab-like composite shapes. The
     * boolean query must report a hit whenever any constituent solid intersects the ray.
     */
    @Test
    void testIntersectsRay() {
        var vs1 = VoxelShape
                .builder()
                .solid(0, 0, 0, 1, 1, 1)
                .build();
        assertTrue(vs1.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs2 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 1.0, 0.1, 1.0)
                .solid(0.0, 0.9, 0.0, 1.0, 1.0, 1.0)
                .solid(0.0, 0.1, 0.0, 0.1, 0.9, 1.0)
                .solid(0.9, 0.1, 0.0, 1.0, 0.9, 1.0)
                .solid(0.1, 0.1, 0.0, 0.9, 0.9, 0.1)
                .solid(0.1, 0.1, 0.9, 0.9, 0.9, 1.0)
                .build();
        assertTrue(vs2.intersectsRay(0, 0, 0, 1, 1, 1));

        var vs3 = VoxelShape.builder()
                .solid(0.0, 0.0, 0.0, 0.4, 1.0, 1.0)
                .solid(0.6, 0.0, 0.0, 1.0, 1.0, 1.0)
                .solid(0.4, 0.0, 0.0, 0.6, 1.0, 0.4)
                .solid(0.4, 0.0, 0.6, 0.6, 1.0, 1.0)
                .build();
        assertFalse(vs3.intersectsRay(0.5, 0, 0.5, 0, 1, 0));

        var vs4 = VoxelShape.builder()
                .solid(0, 0.4, 0, 1, 0.6, 1)
                .build();
        assertTrue(vs4.intersectsRay(0, 0, 0, 1, 1, 1));
    }

    /**
     * Checks ray output for full, empty, and separated-solid shapes. Hit spans must
     * combine the nearest entry and farthest exit, while misses overwrite output with the
     * infinity sentinel.
     */
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

        var vs2 = VoxelShape.builder()
                .solid(0, 0, 0, 1, 1, 1)
                .solid(0.0, 0.0, 0.0, 0.1, 1, 1)
                .solid(0.9, 0.0, 0.0, 1.0, 1, 1)
                .solid(0.0, 0.0, 0.0, 1.0, 0.1, 1)
                .solid(0.0, 0.9, 0.0, 1.0, 1.0, 1)
                .solid(0.0, 0.0, 0.0, 1.0, 1.0, 0.1)
                .solid(0.0, 0.0, 0.9, 1.0, 1.0, 1)
                .build();
        assertTrue(vs2.intersectsRay(0, 0, 0, 1, 1, 1, result));
        assertEquals(0, result.x);
        assertEquals(1, result.y);

        result.set(0, 0);
        var vs3 = VoxelShape
                .builder()
                .build();
        assertFalse(vs3.intersectsRay(0, 0, 0, 1, 1, 1, result));
        assertEquals(Double.NEGATIVE_INFINITY, result.x);
        assertEquals(Double.POSITIVE_INFINITY, result.y);

        var vs4 = VoxelShape.builder()
                .solid(0, 0.2, 0, 1, 0.4, 1)
                .solid(0, 0.6, 0, 1, 0.8, 1)
                .build();
        assertTrue(vs4.intersectsRay(0, 0, 0, 0, 1, 0, result));
        assertEquals(0.2, result.x);
        assertEquals(0.8, result.y);
    }
}
