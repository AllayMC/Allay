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
 * {@code MathTest} exercises dimension-bearing coordinates and the numerical geometry
 * helpers used by server code. The nested groups separate conversion, orientation,
 * approximation, and mutable-box behavior.
 * @author Cool_Loong | daoge_cmd
 */
@ExtendWith({MockitoExtension.class})
public class MathTest {

    /**
     * Mock dimension associated with position and location fixtures.
     */
    @Mock
    Dimension dim;

    /**
     * {@code PositionAndLocationTests} checks position and orientation mutation while
     * retaining the supplied dimension.
     */
    @Nested
    class PositionAndLocationTests {

        /**
         * Creates an integer position with a mock dimension and then changes its
         * coordinates. The assertions protect coordinate mutation while retaining a
         * reachable dimension association.
         */
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

        /**
         * Creates an integer location with zero orientation and changes coordinates and
         * angles. The assertions protect independent position, pitch, and yaw updates for
         * the supplied dimension.
         */
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

    /**
     * {@code VectorConversionTests} checks component conversion and rounding behavior for
     * integer and floating vectors.
     */
    @Nested
    class VectorConversionTests {

        /**
         * Converts a double vector whose components are exactly representable as floats.
         * The result must preserve all three component values within the stated comparison
         * tolerance.
         */
        @Test
        void testToVec3f() {
            var vec3d = new Vector3d(1.5, 2.5, 3.5);
            var vec3f = MathUtils.toVec3f(vec3d);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(1.5f, vec3f.x(), 0.001f);
            assertEquals(2.5f, vec3f.y(), 0.001f);
            assertEquals(3.5f, vec3f.z(), 0.001f);
        }

        /**
         * Floors a double vector containing positive fractions and a negative fraction.
         * The assertions protect downward rounding, including the difference between floor
         * and truncation below zero.
         */
        @Test
        void testFloorVector3d() {
            var vec = new Vector3d(1.9, 2.1, -0.5);
            var floored = MathUtils.floor(vec);

            assertEquals(1, floored.x());
            assertEquals(2, floored.y());
            assertEquals(-1, floored.z());
        }

        /**
         * Floors a float vector containing positive fractions and a negative fraction. The
         * assertions protect downward rounding, including the difference between floor and
         * truncation below zero.
         */
        @Test
        void testFloorVector3f() {
            var vec = new Vector3f(1.9f, 2.1f, -0.5f);
            var floored = MathUtils.floor(vec);

            assertEquals(1, floored.x());
            assertEquals(2, floored.y());
            assertEquals(-1, floored.z());
        }

        /**
         * Ceils a double vector containing positive fractions and a negative fraction. The
         * assertions protect upward rounding on both sides of zero rather than truncation
         * toward a fixed direction.
         */
        @Test
        void testCeilVector3d() {
            var vec = new Vector3d(1.1, 2.9, -0.5);
            var ceiled = MathUtils.ceil(vec);

            assertEquals(2, ceiled.x());
            assertEquals(3, ceiled.y());
            assertEquals(0, ceiled.z());
        }

        /**
         * Ceils a float vector containing positive fractions and a negative fraction. The
         * assertions protect upward rounding on both sides of zero rather than truncation
         * toward a fixed direction.
         */
        @Test
        void testCeilVector3f() {
            var vec = new Vector3f(1.1f, 2.9f, -0.5f);
            var ceiled = MathUtils.ceil(vec);

            assertEquals(2, ceiled.x());
            assertEquals(3, ceiled.y());
            assertEquals(0, ceiled.z());
        }

        /**
         * Centers an integer block coordinate on all three axes. The resulting vector must
         * add half a block to every supplied coordinate within the stated comparison
         * tolerance.
         */
        @Test
        void testCenter() {
            var vec = new Vector3i(1, 2, 3);
            var centered = MathUtils.center(vec);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(1.5, centered.x(), 0.001);
            assertEquals(2.5, centered.y(), 0.001);
            assertEquals(3.5, centered.z(), 0.001);
        }
    }

    /**
     * {@code RoundingTests} checks decimal-place rounding for ordinary positive and
     * negative values.
     */
    @Nested
    class RoundingTests {

        /**
         * Rounds a fractional value and a half-integer with zero decimal places. The
         * assertions protect ordinary nearest-integer rounding and the positive half-step
         * result.
         */
        @Test
        void testRoundPrecision0() {
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(3.0, MathUtils.round(3.14159, 0), 0.001);
            assertEquals(4.0, MathUtils.round(3.5, 0), 0.001);
        }

        /**
         * Rounds two fractional values to two decimal places. The resulting values must
         * retain the requested decimal precision within the assertions' comparison
         * tolerance.
         */
        @Test
        void testRoundPrecision2() {
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(3.14, MathUtils.round(3.14159, 2), 0.001);
            assertEquals(2.72, MathUtils.round(2.71828, 2), 0.001);
        }

        /**
         * Rounds a fractional value to four decimal places. The assertion protects the
         * expected final digit using a tolerance smaller than the requested decimal step.
         */
        @Test
        void testRoundPrecision4() {
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(3.1416, MathUtils.round(3.14159, 4), 0.00001);
        }

        /**
         * Rounds a negative fractional value to two decimal places. The assertion protects
         * the signed result and requested precision within its explicit comparison
         * tolerance.
         */
        @Test
        void testRoundNegative() {
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-3.14, MathUtils.round(-3.14159, 2), 0.001);
        }
    }

    /**
     * {@code FastMathTests} checks sampled trigonometry and reciprocal-square-root
     * approximation on representative inputs.
     */
    @Nested
    class FastMathTests {

        /**
         * Approximates the reciprocal square root of a positive float. The result is
         * compared with ordinary square-root division using an absolute tolerance
         * appropriate to this approximate operation.
         */
        @Test
        void testFastFloatInverseSqrt() {
            float value = 4.0f;
            float result = MathUtils.fastFloatInverseSqrt(value);
            float expected = 1.0f / (float) Math.sqrt(value);

            // The tolerance admits the one-step approximation rather than requiring exact
            // square-root division.
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01f);
        }

        /**
         * Approximates the reciprocal square root of a positive double. The result is
         * compared with ordinary square-root division using an absolute tolerance
         * appropriate to this approximate operation.
         */
        @Test
        void testFastDoubleInverseSqrt() {
            double value = 4.0;
            double result = MathUtils.fastDoubleInverseSqrt(value);
            double expected = 1.0 / Math.sqrt(value);

            // The tolerance admits the one-step approximation rather than requiring exact
            // square-root division.
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01);
        }

        /**
         * Samples the float-angle sine lookup near a quarter turn. The result is compared
         * with {@code Math.sin} using a tolerance that allows lookup-table angular
         * quantization.
         */
        @Test
        void testFastSinFloat() {
            float radian = (float) (Math.PI / 2);
            double result = MathUtils.fastSin(radian);
            double expected = Math.sin(radian);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01);
        }

        /**
         * Samples the double-angle sine lookup at a quarter turn. The result is compared
         * with {@code Math.sin} using a tolerance that allows lookup-table angular
         * quantization.
         */
        @Test
        void testFastSinDouble() {
            double radian = Math.PI / 2;
            double result = MathUtils.fastSin(radian);
            double expected = Math.sin(radian);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01);
        }

        /**
         * Samples the float-angle cosine lookup at zero radians. The result is compared
         * with {@code Math.cos} using a tolerance that allows lookup-table angular
         * quantization.
         */
        @Test
        void testFastCosFloat() {
            float radian = 0.0f;
            double result = MathUtils.fastCos(radian);
            double expected = Math.cos(radian);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01);
        }

        /**
         * Samples the double-angle cosine lookup at zero radians. The result is compared
         * with {@code Math.cos} using a tolerance that allows lookup-table angular
         * quantization.
         */
        @Test
        void testFastCosDouble() {
            double radian = 0.0;
            double result = MathUtils.fastCos(radian);
            double expected = Math.cos(radian);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(expected, result, 0.01);
        }
    }

    /**
     * {@code RangeTests} checks inclusive bound handling for positive and negative ranges.
     */
    @Nested
    class RangeTests {

        /**
         * Checks an interior value and both endpoints of a positive interval. Every case
         * must be accepted, protecting the inclusive lower and upper bound convention.
         */
        @Test
        void testIsInRangeTrue() {
            assertTrue(MathUtils.isInRange(0, 5, 10));
            assertTrue(MathUtils.isInRange(0, 0, 10));
            assertTrue(MathUtils.isInRange(0, 10, 10));
        }

        /**
         * Checks values immediately outside each side of a positive interval. Both cases
         * must be rejected, protecting exclusion beyond the inclusive endpoints.
         */
        @Test
        void testIsInRangeFalse() {
            assertFalse(MathUtils.isInRange(0, -1, 10));
            assertFalse(MathUtils.isInRange(0, 11, 10));
        }

        /**
         * Checks an interior and an out-of-range value in a negative interval. The
         * assertions protect the same inclusive comparison behavior below zero.
         */
        @Test
        void testIsInRangeNegative() {
            assertTrue(MathUtils.isInRange(-10, -5, 0));
            assertFalse(MathUtils.isInRange(-10, -11, 0));
        }
    }

    /**
     * {@code DirectionVectorTests} checks degree-angle conversion and zero-vector
     * orientation fallback behavior.
     */
    @Nested
    class DirectionVectorTests {

        /**
         * Converts zero yaw and pitch into a direction vector. The components must point
         * along positive {@code z}, protecting the server's forward-axis orientation
         * convention.
         */
        @Test
        void testGetDirectionVectorForward() {
            var direction = MathUtils.getDirectionVector(0, 0);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, direction.x(), 0.01);
            assertEquals(0, direction.y(), 0.01);
            assertEquals(1, direction.z(), 0.01);
        }

        /**
         * Converts a ninety-degree downward pitch into a direction vector. The components
         * must point along negative {@code y}, protecting the sign convention for positive
         * pitch.
         */
        @Test
        void testGetDirectionVectorDown() {
            var direction = MathUtils.getDirectionVector(0, 90);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, direction.x(), 0.01);
            assertEquals(-1, direction.y(), 0.01);
            assertEquals(0, direction.z(), 0.01);
        }

        /**
         * Converts a negative ninety-degree pitch into a direction vector. The components
         * must point along positive {@code y}, protecting the sign convention for upward
         * pitch.
         */
        @Test
        void testGetDirectionVectorUp() {
            var direction = MathUtils.getDirectionVector(0, -90);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, direction.x(), 0.01);
            assertEquals(1, direction.y(), 0.01);
            assertEquals(0, direction.z(), 0.01);
        }

        /**
         * Derives yaw from a vector with no horizontal length. The result must be zero,
         * protecting the fallback that avoids dividing by a zero magnitude.
         */
        @Test
        void testGetYawFromVectorZero() {
            var zeroVector = new Vector3d(0, 0, 0);
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, MathUtils.getYawFromVector(zeroVector), 0.01);
        }

        /**
         * Derives yaw from a vector along positive {@code z}. The result must be zero,
         * protecting the horizontal reference direction used by orientation conversion.
         */
        @Test
        void testGetYawFromVectorPositiveZ() {
            var vector = new Vector3d(0, 0, 1);
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, MathUtils.getYawFromVector(vector), 0.01);
        }

        /**
         * Derives pitch from a zero-length vector. The result must be zero, protecting the
         * fallback that avoids dividing by a zero magnitude during angle conversion.
         */
        @Test
        void testGetPitchFromVectorZero() {
            var zeroVector = new Vector3d(0, 0, 0);
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, MathUtils.getPitchFromVector(zeroVector), 0.01);
        }

        /**
         * Derives pitch from a vector along negative {@code y}. The result must be ninety
         * degrees, protecting the server's positive-downward pitch convention.
         */
        @Test
        void testGetPitchFromVectorDown() {
            var vector = new Vector3d(0, -1, 0);
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(90, MathUtils.getPitchFromVector(vector), 0.01);
        }

        /**
         * Derives pitch from a vector along positive {@code y}. The result must be
         * negative ninety degrees, protecting the sign of upward orientation.
         */
        @Test
        void testGetPitchFromVectorUp() {
            var vector = new Vector3d(0, 1, 0);
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-90, MathUtils.getPitchFromVector(vector), 0.01);
        }
    }

    /**
     * {@code NaNCheckTests} checks NaN detection in vector and location coordinate
     * components.
     */
    @Nested
    class NaNCheckTests {

        /**
         * Checks {@code Vector3f} values with NaN in each coordinate position. Every
         * supplied case must be detected, protecting coordinate coverage without depending
         * on a particular axis.
         */
        @Test
        void testHasNaNVector3fTrue() {
            assertTrue(MathUtils.hasNaN(new Vector3f(Float.NaN, 0, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3f(0, Float.NaN, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3f(0, 0, Float.NaN)));
        }

        /**
         * Checks finite {@code Vector3f} coordinates without any NaN component. The
         * detector must return {@code false}, protecting ordinary finite values from false
         * NaN classification.
         */
        @Test
        void testHasNaNVector3fFalse() {
            assertFalse(MathUtils.hasNaN(new Vector3f(1, 2, 3)));
            assertFalse(MathUtils.hasNaN(new Vector3f(0, 0, 0)));
        }

        /**
         * Checks {@code Vector3d} values with NaN in each coordinate position. Every
         * supplied case must be detected, protecting coordinate coverage without depending
         * on a particular axis.
         */
        @Test
        void testHasNaNVector3dTrue() {
            assertTrue(MathUtils.hasNaN(new Vector3d(Double.NaN, 0, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3d(0, Double.NaN, 0)));
            assertTrue(MathUtils.hasNaN(new Vector3d(0, 0, Double.NaN)));
        }

        /**
         * Checks finite {@code Vector3d} coordinates without any NaN component. The
         * detector must return {@code false}, protecting ordinary finite values from false
         * NaN classification.
         */
        @Test
        void testHasNaNVector3dFalse() {
            assertFalse(MathUtils.hasNaN(new Vector3d(1, 2, 3)));
            assertFalse(MathUtils.hasNaN(new Vector3d(0, 0, 0)));
        }

        /**
         * Checks {@code Location3f} values with NaN in each coordinate position. Every
         * supplied case must be detected, protecting coordinate coverage without depending
         * on a particular axis.
         */
        @Test
        void testHasNaNLocation3fTrue() {
            assertTrue(MathUtils.hasNaN(new Location3f(Float.NaN, 0, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3f(0, Float.NaN, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3f(0, 0, Float.NaN, null)));
        }

        /**
         * Checks finite {@code Location3f} coordinates without any NaN component. The
         * detector must return {@code false}, protecting ordinary finite values from false
         * NaN classification.
         */
        @Test
        void testHasNaNLocation3fFalse() {
            assertFalse(MathUtils.hasNaN(new Location3f(1, 2, 3, null)));
        }

        /**
         * Checks {@code Location3d} values with NaN in each coordinate position. Every
         * supplied case must be detected, protecting coordinate coverage without depending
         * on a particular axis.
         */
        @Test
        void testHasNaNLocation3dTrue() {
            assertTrue(MathUtils.hasNaN(new Location3d(Double.NaN, 0, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3d(0, Double.NaN, 0, null)));
            assertTrue(MathUtils.hasNaN(new Location3d(0, 0, Double.NaN, null)));
        }

        /**
         * Checks finite {@code Location3d} coordinates without any NaN component. The
         * detector must return {@code false}, protecting ordinary finite values from false
         * NaN classification.
         */
        @Test
        void testHasNaNLocation3dFalse() {
            assertFalse(MathUtils.hasNaN(new Location3d(1, 2, 3, null)));
        }
    }

    /**
     * {@code NormalizeTests} checks normalization of nonzero vectors and preservation of
     * zero vectors.
     */
    @Nested
    class NormalizeTests {

        /**
         * Normalizes a nonzero {@code Vector3f} with a three-four-five magnitude. The
         * resulting length must be one within the comparison tolerance, protecting the
         * ordinary normalization path.
         */
        @Test
        void testNormalizeIfNotZeroVector3f() {
            var vec = new Vector3f(3, 0, 4);
            var normalized = MathUtils.normalizeIfNotZero(vec);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(1.0f, normalized.length(), 0.001f);
        }

        /**
         * Normalizes a zero {@code Vector3f} through the guarded helper. The result must
         * keep zero coordinates without introducing NaN, protecting the zero-length
         * fallback.
         */
        @Test
        void testNormalizeIfNotZeroVector3fZero() {
            var vec = new Vector3f(0, 0, 0);
            var result = MathUtils.normalizeIfNotZero(vec);

            assertFalse(MathUtils.hasNaN(result));
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, result.x(), 0.001f);
            assertEquals(0, result.y(), 0.001f);
            assertEquals(0, result.z(), 0.001f);
        }

        /**
         * Normalizes a nonzero {@code Vector3d} with a three-four-five magnitude. The
         * resulting length must be one within the comparison tolerance, protecting the
         * ordinary normalization path.
         */
        @Test
        void testNormalizeIfNotZeroVector3d() {
            var vec = new Vector3d(3, 0, 4);
            var normalized = MathUtils.normalizeIfNotZero(vec);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(1.0, normalized.length(), 0.001);
        }

        /**
         * Normalizes a zero {@code Vector3d} through the guarded helper. The result must
         * keep zero coordinates without introducing NaN, protecting the zero-length
         * fallback.
         */
        @Test
        void testNormalizeIfNotZeroVector3dZero() {
            var vec = new Vector3d(0, 0, 0);
            var result = MathUtils.normalizeIfNotZero(vec);

            assertFalse(MathUtils.hasNaN(result));
            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, result.x(), 0.001);
            assertEquals(0, result.y(), 0.001);
            assertEquals(0, result.z(), 0.001);
        }
    }

    /**
     * {@code AABBGrowTests} checks mutable bounding-box expansion across individual faces
     * and all coordinate axes.
     */
    @Nested
    class AABBGrowTests {

        /**
         * Expands one box by different amounts on all coordinate axes. The assertions
         * protect subtraction from each minimum and addition to each maximum in the
         * supplied mutable box.
         */
        @Test
        void testGrowWithVector() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            var growth = new Vector3d(1, 2, 3);

            MathUtils.grow(aabb, growth);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-1, aabb.minX, 0.001);
            assertEquals(-2, aabb.minY, 0.001);
            assertEquals(-3, aabb.minZ, 0.001);
            assertEquals(11, aabb.maxX, 0.001);
            assertEquals(12, aabb.maxY, 0.001);
            assertEquals(13, aabb.maxZ, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.DOWN} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceDown() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.DOWN, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-5, aabb.minY, 0.001);
            assertEquals(10, aabb.maxY, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.UP} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceUp() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.UP, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, aabb.minY, 0.001);
            assertEquals(15, aabb.maxY, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.NORTH} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceNorth() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.NORTH, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-5, aabb.minZ, 0.001);
            assertEquals(10, aabb.maxZ, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.SOUTH} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceSouth() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.SOUTH, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, aabb.minZ, 0.001);
            assertEquals(15, aabb.maxZ, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.WEST} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceWest() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.WEST, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(-5, aabb.minX, 0.001);
            assertEquals(10, aabb.maxX, 0.001);
        }

        /**
         * Grows a box toward {@code BlockFace.EAST} by the supplied distance. Only the
         * selected axis boundary may move, protecting directional growth and the opposite
         * boundary's original value.
         */
        @Test
        void testGrowBlockFaceEast() {
            var aabb = new AABBd(0, 0, 0, 10, 10, 10);
            MathUtils.grow(aabb, BlockFace.EAST, 5);

            // Allow rounding differences when comparing the expected floating-point geometry values.
            assertEquals(0, aabb.minX, 0.001);
            assertEquals(15, aabb.maxX, 0.001);
        }
    }
}
