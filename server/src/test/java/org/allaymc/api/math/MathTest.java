package org.allaymc.api.math;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.primitives.AABBd;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cool_Loong | daoge_cmd
 */
@ExtendWith({MockitoExtension.class})
public class MathTest {

    @Mock
    Dimension dim;

    @Nested
    class PositionAndLocationTests {

        @Test
        void testPosition() {
            var pos = new Position3i(1, 1, 1, dim);
            assertNotNull(pos.dimension());
            assertEquals(1, pos.x());
            assertEquals(1, pos.y());
            assertEquals(1, pos.z());
            pos.set(2, 3, 4);
            assertEquals(2, pos.x());
            assertEquals(3, pos.y());
            assertEquals(4, pos.z());
        }

        @Test
        void testLocation() {
            var loc = new Location3i(1, 1, 1, dim);
            assertNotNull(loc.dimension());
            assertEquals(0d, loc.yaw());
            assertEquals(0d, loc.pitch());
            assertEquals(1, loc.x());
            assertEquals(1, loc.y());
            assertEquals(1, loc.z());
            loc.set(2, 3, 4);
            assertEquals(2, loc.x());
            assertEquals(3, loc.y());
            assertEquals(4, loc.z());
            loc.setPitch(3);
            loc.setYaw(2);
            assertEquals(3d, loc.pitch());
            assertEquals(2d, loc.yaw());
        }
    }

    @Nested
    class VectorConversionTests {

        @Test
        void testToVec3f() {
            var vec3d = new Vector3d(1.5, 2.5, 3.5);
            var vec3f = MathUtils.toVec3f(vec3d);

            assertEquals(1.5f, vec3f.x(), 0.001f);
            assertEquals(2.5f, vec3f.y(), 0.001f);
            assertEquals(3.5f, vec3f.z(), 0.001f);
        }

        @Test
        void testFloorVector3d() {
            var vec = new Vector3d(1.9, 2.1, -0.5);
            var floored = MathUtils.floor(vec);

            assertEquals(1, floored.x());
            assertEquals(2, floored.y());
            assertEquals(-1, floored.z());
        }

        @Test
        void testFloorVector3f() {
            var vec = new Vector3f(1.9f, 2.1f, -0.5f);
            var floored = MathUtils.floor(vec);

            assertEquals(1, floored.x());
            assertEquals(2, floored.y());
            assertEquals(-1, floored.z());
        }

        @Test
        void testCeilVector3d() {
            var vec = new Vector3d(1.1, 2.9, -0.5);
            var ceiled = MathUtils.ceil(vec);

            assertEquals(2, ceiled.x());
            assertEquals(3, ceiled.y());
            assertEquals(0, ceiled.z());
        }

        @Test
        void testCeilVector3f() {
            var vec = new Vector3f(1.1f, 2.9f, -0.5f);
            var ceiled = MathUtils.ceil(vec);

            assertEquals(2, ceiled.x());
            assertEquals(3, ceiled.y());
            assertEquals(0, ceiled.z());
        }

        @Test
        void testCenter() {
            var vec = new Vector3i(1, 2, 3);
            var centered = MathUtils.center(vec);

            assertEquals(1.5, centered.x(), 0.001);
            assertEquals(2.5, centered.y(), 0.001);
            assertEquals(3.5, centered.z(), 0.001);
        }
    }

    @Nested
    class RoundingTests {

        @Test
        void testRoundPrecision0() {
            assertEquals(3.0, MathUtils.round(3.14159, 0), 0.001);
            assertEquals(4.0, MathUtils.round(3.5, 0), 0.001);
        }

        @Test
        void testRoundPrecision2() {
            assertEquals(3.14, MathUtils.round(3.14159, 2), 0.001);
            assertEquals(2.72, MathUtils.round(2.71828, 2), 0.001);
        }

        @Test
        void testRoundPrecision4() {
            assertEquals(3.1416, MathUtils.round(3.14159, 4), 0.00001);
        }

        @Test
        void testRoundNegative() {
            assertEquals(-3.14, MathUtils.round(-3.14159, 2), 0.001);
        }
    }

    @Nested
    class FastMathTests {

        @Test
        void testFastFloatInverseSqrt() {
            float value = 4.0f;
            float result = MathUtils.fastFloatInverseSqrt(value);
            float expected = 1.0f / (float) Math.sqrt(value);

            // Fast inverse sqrt is approximate
            assertEquals(expected, result, 0.01f);
        }

        @Test
        void testFastDoubleInverseSqrt() {
            double value = 4.0;
            double result = MathUtils.fastDoubleInverseSqrt(value);
            double expected = 1.0 / Math.sqrt(value);

            // Fast inverse sqrt is approximate
            assertEquals(expected, result, 0.01);
        }

        @Test
        void testFastSinFloat() {
            float radian = (float) (Math.PI / 2);
            double result = MathUtils.fastSin(radian);
            double expected = Math.sin(radian);

            assertEquals(expected, result, 0.01);
        }

        @Test
        void testFastSinDouble() {
            double radian = Math.PI / 2;
            double result = MathUtils.fastSin(radian);
            double expected = Math.sin(radian);

            assertEquals(expected, result, 0.01);
        }

        @Test
        void testFastCosFloat() {
            float radian = 0.0f;
            double result = MathUtils.fastCos(radian);
            double expected = Math.cos(radian);

            assertEquals(expected, result, 0.01);
        }

        @Test
        void testFastCosDouble() {
            double radian = 0.0;
            double result = MathUtils.fastCos(radian);
            double expected = Math.cos(radian);

            assertEquals(expected, result, 0.01);
        }
    }

    @Nested
    class RangeTests {

        @Test
        void testIsInRangeTrue() {
            assertTrue(MathUtils.isInRange(0, 5, 10));
            assertTrue(MathUtils.isInRange(0, 0, 10));
            assertTrue(MathUtils.isInRange(0, 10, 10));
        }

        @Test
        void testIsInRangeFalse() {
            assertFalse(MathUtils.isInRange(0, -1, 10));
            assertFalse(MathUtils.isInRange(0, 11, 10));
        }

        @Test
        void testIsInRangeNegative() {
            assertTrue(MathUtils.isInRange(-10, -5, 0));
            assertFalse(MathUtils.isInRange(-10, -11, 0));
        }
    }

    @Nested
    class DirectionVectorTests {

        @Test
        void testGetDirectionVectorForward() {
            // Yaw 0, Pitch 0 should point in positive Z direction (forward)
            var direction = MathUtils.getDirectionVector(0, 0);

            assertEquals(0, direction.x(), 0.01);
            assertEquals(0, direction.y(), 0.01);
            assertEquals(1, direction.z(), 0.01);
        }

        @Test
        void testGetDirectionVectorDown() {
            // Pitch 90 should point down
            var direction = MathUtils.getDirectionVector(0, 90);

            assertEquals(0, direction.x(), 0.01);
            assertEquals(-1, direction.y(), 0.01);
            assertEquals(0, direction.z(), 0.01);
        }

        @Test
        void testGetDirectionVectorUp() {
            // Pitch -90 should point up
            var direction = MathUtils.getDirectionVector(0, -90);

            assertEquals(0, direction.x(), 0.01);
            assertEquals(1, direction.y(), 0.01);
            assertEquals(0, direction.z(), 0.01);
        }

        @Test
        void testGetYawFromVectorZero() {
            var zeroVector = new Vector3d(0, 0, 0);
            assertEquals(0, MathUtils.getYawFromVector(zeroVector), 0.01);
        }

        @Test
        void testGetYawFromVectorPositiveZ() {
            var vector = new Vector3d(0, 0, 1);
            assertEquals(0, MathUtils.getYawFromVector(vector), 0.01);
        }

        @Test
        void testGetPitchFromVectorZero() {
            var zeroVector = new Vector3d(0, 0, 0);
            assertEquals(0, MathUtils.getPitchFromVector(zeroVector), 0.01);
        }

        @Test
        void testGetPitchFromVectorDown() {
            var vector = new Vector3d(0, -1, 0);
            assertEquals(90, MathUtils.getPitchFromVector(vector), 0.01);
        }

        @Test
        void testGetPitchFromVectorUp() {
            var vector = new Vector3d(0, 1, 0);
            assertEquals(-90, MathUtils.getPitchFromVector(vector), 0.01);
        }
    }

    @Nested
    class NaNCheckTests {

        @Test
        void testHasNaNVector3fTrue() {
            assertTrue(MathUtils.hasNaN(new Vector3f(Float.NaN, 0, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3f(0, Float.NaN, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3f(0, 0, Float.NaN)));
        }

        @Test
        void testHasNaNVector3fFalse() {
            assertFalse(MathUtils.hasNaN(new Vector3f(1, 2, 3)));
            assertFalse(MathUtils.hasNaN(new Vector3f(0, 0, 0)));
        }

        @Test
        void testHasNaNVector3dTrue() {
            assertTrue(MathUtils.hasNaN(new Vector3d(Double.NaN, 0, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3d(0, Double.NaN, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3d(0, 0, Double.NaN)));
        }

        @Test
        void testHasNaNVector3dFalse() {
            assertFalse(MathUtils.hasNaN(new Vector3d(1, 2, 3)));
            assertFalse(MathUtils.hasNaN(new Vector3d(0, 0, 0)));
        }

        @Test
        void testHasNaNLocation3fTrue() {
            assertTrue(MathUtils.hasNaN(new Location3f(Float.NaN, 0, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3f(0, Float.NaN, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3f(0, 0, Float.NaN, null)));
        }

        @Test
        void testHasNaNLocation3fFalse() {
            assertFalse(MathUtils.hasNaN(new Location3f(1, 2, 3, null)));
        }

        @Test
        void testHasNaNLocation3dTrue() {
            assertTrue(MathUtils.hasNaN(new Location3d(Double.NaN, 0, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3d(0, Double.NaN, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3d(0, 0, Double.NaN, null)));
        }

        @Test
        void testHasNaNLocation3dFalse() {
            assertFalse(MathUtils.hasNaN(new Location3d(1, 2, 3, null)));
        }
    }

    @Nested
    class NormalizeTests {

        @Test
        void testNormalizeIfNotZeroVector3f() {
            var vec = new Vector3f(3, 0, 4);
            var normalized = MathUtils.normalizeIfNotZero(vec);

            assertEquals(1.0f, normalized.length(), 0.001f);
        }

        @Test
        void testNormalizeIfNotZeroVector3fZero() {
            var vec = new Vector3f(0, 0, 0);
            var result = MathUtils.normalizeIfNotZero(vec);

            // Zero vector should remain zero, not become NaN
            assertFalse(MathUtils.hasNaN(result));
            assertEquals(0, result.x(), 0.001f);
            assertEquals(0, result.y(), 0.001f);
            assertEquals(0, result.z(), 0.001f);
        }

        @Test
        void testNormalizeIfNotZeroVector3d() {
            var vec = new Vector3d(3, 0, 4);
            var normalized = MathUtils.normalizeIfNotZero(vec);

            assertEquals(1.0, normalized.length(), 0.001);
        }

        @Test
        void testNormalizeIfNotZeroVector3dZero() {
            var vec = new Vector3d(0, 0, 0);
            var result = MathUtils.normalizeIfNotZero(vec);

            // Zero vector should remain zero, not become NaN
            assertFalse(MathUtils.hasNaN(result));
            assertEquals(0, result.x(), 0.001);
            assertEquals(0, result.y(), 0.001);
            assertEquals(0, result.z(), 0.001);
        }
    }

    @Nested
    class AABBGrowTests {

        @Test
        void testGrowWithVector() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            var growth = new Vector3d(1, 2, 3);

            MathUtils.grow(aabb, growth);

            assertEquals(-1, aabb.minX, 0.001);
            assertEquals(-2, aabb.minY, 0.001);
            assertEquals(-3, aabb.minZ, 0.001);
            assertEquals(11, aabb.maxX, 0.001);
            assertEquals(12, aabb.maxY, 0.001);
            assertEquals(13, aabb.maxZ, 0.001);
        }

        @Test
        void testGrowBlockFaceDown() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.DOWN, 5);

            assertEquals(-5, aabb.minY, 0.001);
            assertEquals(10, aabb.maxY, 0.001);
        }

        @Test
        void testGrowBlockFaceUp() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.UP, 5);

            assertEquals(0, aabb.minY, 0.001);
            assertEquals(15, aabb.maxY, 0.001);
        }

        @Test
        void testGrowBlockFaceNorth() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.NORTH, 5);

            assertEquals(-5, aabb.minZ, 0.001);
            assertEquals(10, aabb.maxZ, 0.001);
        }

        @Test
        void testGrowBlockFaceSouth() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.SOUTH, 5);

            assertEquals(0, aabb.minZ, 0.001);
            assertEquals(15, aabb.maxZ, 0.001);
        }

        @Test
        void testGrowBlockFaceWest() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.WEST, 5);

            assertEquals(-5, aabb.minX, 0.001);
            assertEquals(10, aabb.maxX, 0.001);
        }

        @Test
        void testGrowBlockFaceEast() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.EAST, 5);

            assertEquals(0, aabb.minX, 0.001);
            assertEquals(15, aabb.maxX, 0.001);
        }
    }
}
