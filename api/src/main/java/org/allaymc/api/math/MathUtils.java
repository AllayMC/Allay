package org.allaymc.api.math;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3fc;
import org.joml.*;
import org.joml.primitives.AABBd;

import java.lang.Math;

import static java.lang.StrictMath.*;

/**
 * {@code MathUtils} converts vectors, derives orientation angles, and supplies numerical
 * approximations used by geometry callers. Vector conversion methods allocate results,
 * while normalization and bounding-box growth modify supplied objects. Direction angles
 * use degrees; lookup-table trigonometric arguments use radians.
 * @author Cool_Loong | daoge_cmd
 */
@UtilityClass
public final class MathUtils {

    /**
     * {@code SIN_LOOK_UP_TABLE} holds one sampled sine value per unsigned sixteen-bit turn
     * index. The fast trigonometric methods wrap their converted angle into this table.
     */
    private static final double[] SIN_LOOK_UP_TABLE = new double[65536];

    // Precompute one complete turn so fastSin and fastCos can reuse the same periodic samples.
    static {
        for (int i = 0; i < 65536; i++) {
            // Convert the table index to radians over one complete turn.
            SIN_LOOK_UP_TABLE[i] = Math.sin(i * Math.PI * 2.0D / 65536.0d);
        }
    }

    /**
     * Returns a new single-precision copy of the supplied double-precision vector. Each
     * coordinate is narrowed to {@code float}, so the result may lose precision and shares
     * no coordinate storage with {@code vec}.
     */
    public static Vector3fc toVec3f(Vector3dc vec) {
        // Narrow coordinates only after allocating storage independent of the source vector.
        return vec.get(new Vector3f());
    }

    /**
     * Returns a new integer vector by rounding each coordinate downward. The input vector
     * remains unchanged; conversion to {@code int} follows Java narrowing for nonfinite or
     * out-of-range coordinates.
     */
    public static Vector3i floor(Vector3dc vector3d) {
        return new Vector3i((int) Math.floor(vector3d.x()), (int) Math.floor(vector3d.y()), (int) Math.floor(vector3d.z()));
    }

    /**
     * Returns a new integer vector by rounding each coordinate downward. The input vector
     * remains unchanged; conversion to {@code int} follows Java narrowing for nonfinite or
     * out-of-range coordinates.
     */
    public static Vector3i floor(Vector3fc vector3f) {
        return new Vector3i((int) Math.floor(vector3f.x()), (int) Math.floor(vector3f.y()), (int) Math.floor(vector3f.z()));
    }

    /**
     * Returns a new integer vector by rounding each coordinate upward. The input vector
     * remains unchanged; conversion to {@code int} follows Java narrowing for nonfinite or
     * out-of-range coordinates.
     */
    public static Vector3i ceil(Vector3dc vector3d) {
        return new Vector3i((int) Math.ceil(vector3d.x()), (int) Math.ceil(vector3d.y()), (int) Math.ceil(vector3d.z()));
    }

    /**
     * Returns a new integer vector by rounding each coordinate upward. The input vector
     * remains unchanged; conversion to {@code int} follows Java narrowing for nonfinite or
     * out-of-range coordinates.
     */
    public static Vector3i ceil(Vector3fc vector3f) {
        return new Vector3i((int) Math.ceil(vector3f.x()), (int) Math.ceil(vector3f.y()), (int) Math.ceil(vector3f.z()));
    }

    /**
     * Returns a new vector at the center of the supplied block coordinates. The half-block
     * offset is applied to all three axes, and {@code vector3i} remains unchanged.
     */
    public static Vector3d center(Vector3ic vector3i) {
        // Half a block moves integer corner coordinates to block centers.
        return new Vector3d(vector3i.x() + 0.5, vector3i.y() + 0.5, vector3i.z() + 0.5);
    }

    /**
     * Returns {@code d} rounded to the requested decimal-place count using
     * {@link Math#round(double)}. A negative {@code precision} rounds to powers of ten
     * above the decimal point; this method does not validate extreme scales.
     */
    public static double round(double d, int precision) {
        // Decimal scaling makes Math.round select the requested decimal place.
        double pow = Math.pow(10, precision);
        return ((double) Math.round(d * pow)) / pow;
    }

    /**
     * Returns a bit-seeded approximation of the reciprocal square root. The approximation
     * applies one Newton refinement and is intended for positive finite inputs; this
     * implementation does not check that input domain.
     */
    public static float fastFloatInverseSqrt(float x) {
        float xHalf = 0.5f * x;
        int reEncode = Float.floatToIntBits(x);
        // Seed reciprocal square root from the float exponent before Newton refinement.
        reEncode = 0x5f3759df - (reEncode >> 1);
        x = Float.intBitsToFloat(reEncode);
        x *= (1.5f - xHalf * x * x);
        return x;
    }

    /**
     * Returns a bit-seeded approximation of the reciprocal square root. The approximation
     * applies one Newton refinement and is intended for positive finite inputs; this
     * implementation does not check that input domain.
     */
    public static double fastDoubleInverseSqrt(double x) {
        double xHalf = 0.5d * x;
        long reEncode = Double.doubleToLongBits(x);
        // Seed reciprocal square root from the double exponent before Newton refinement.
        reEncode = 0x5fe6ec85e7de30daL - (reEncode >> 1);
        x = Double.longBitsToDouble(reEncode);
        x *= (1.5d - xHalf * x * x);
        return x;
    }

    /**
     * Returns whether {@code value} lies between the inclusive supplied bounds. A reversed
     * bound order or any {@code NaN} operand produces {@code false}; this method does not
     * reorder the endpoints.
     */
    public static boolean isInRange(double l, double value, double r) {
        return l <= value && value <= r;
    }

    /**
     * Returns a newly allocated direction vector for the location's orientation. The
     * {@code yaw()} and {@code pitch()} values are interpreted in degrees; coordinate and
     * dimension values do not participate.
     */
    public static Vector3d getDirectionVector(Location3dc location) {
        return getDirectionVector(location.yaw(), location.pitch());
    }

    /**
     * Returns a normalized direction vector for {@code yaw} and {@code pitch} in degrees.
     * Zero angles point along positive {@code z}, and positive {@code pitch} turns the
     * vector downward toward negative {@code y}.
     */
    public static Vector3d getDirectionVector(double yaw, double pitch) {
        // Convert degree angles and align the spherical origin with positive Z.
        var pitch0 = toRadians(pitch + 90);
        var yaw0 = toRadians(yaw + 90);
        var x = sin(pitch0) * cos(yaw0);
        var z = sin(pitch0) * sin(yaw0);
        var y = cos(pitch0);
        return new Vector3d(x, y, z).normalize();
    }

    /**
     * Returns the horizontal direction angle in degrees for {@code vector}. A vector
     * without a horizontal component returns {@code 0}; the result also snaps angles with
     * absolute magnitude below {@code 1E-10} degrees to zero.
     */
    public static double getYawFromVector(Vector3dc vector) {
        double length = vector.x() * vector.x() + vector.z() * vector.z();
        // A direction without the required length has no defined angle; use zero before
        // division would produce NaN.
        if (length == 0) {
            return 0;
        }
        double yaw = toDegrees(asin(-vector.x() / sqrt(length)));
        // Snap numerical residue near zero while preserving the opposite horizontal hemisphere.
        return -vector.z() > 0.0D ? 180.0D - yaw : StrictMath.abs(yaw) < 1E-10 ? 0 : yaw;
    }

    /**
     * Returns the vertical direction angle in degrees for {@code vector}. A zero-length
     * vector returns {@code 0}; positive results point downward, and magnitudes below
     * {@code 1E-10} degrees are snapped to zero.
     */
    public static double getPitchFromVector(Vector3dc vector) {
        double length =
                vector.x() * vector.x() +
                vector.z() * vector.z() +
                vector.y() * vector.y();
        // A direction without the required length has no defined angle; use zero before
        // division would produce NaN.
        if (length == 0) {
            return 0;
        }
        var pitch = toDegrees(asin(-vector.y() / sqrt(length)));
        // Snap numerical residue so axis-aligned directions expose an exact zero angle.
        return StrictMath.abs(pitch) < 1E-10 ? 0 : pitch;
    }

    /**
     * Returns a table-sampled approximation of the sine of {@code radian}. Angle
     * conversion truncates to an integer sample index and wraps to sixteen bits, so this
     * result trades continuous angular precision for table lookup.
     */
    public static double fastSin(float radian) {
        // Convert radians to a periodic sample index; cosine adds a quarter turn.
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F) & 0xFFFF)];
    }

    /**
     * Returns a table-sampled approximation of the sine of {@code radian}. Angle
     * conversion truncates to an integer sample index and wraps to sixteen bits, so this
     * result trades continuous angular precision for table lookup.
     */
    public static double fastSin(double radian) {
        // Convert radians to a periodic sample index; cosine adds a quarter turn.
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F) & 0xFFFF)];
    }

    /**
     * Returns a table-sampled approximation of the cosine of {@code radian}. Angle
     * conversion truncates to an integer sample index and wraps to sixteen bits, so this
     * result trades continuous angular precision for table lookup.
     */
    public static double fastCos(float radian) {
        // Convert radians to a periodic sample index; cosine adds a quarter turn.
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    /**
     * Returns a table-sampled approximation of the cosine of {@code radian}. Angle
     * conversion truncates to an integer sample index and wraps to sixteen bits, so this
     * result trades continuous angular precision for table lookup.
     */
    public static double fastCos(double radian) {
        // Convert radians to a periodic sample index; cosine adds a quarter turn.
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    /**
     * Returns whether any coordinate of the supplied vector is {@code NaN}.
     */
    public static boolean hasNaN(Vector3fc v) {
        return Float.isNaN(v.x()) || Float.isNaN(v.y()) || Float.isNaN(v.z());
    }

    /**
     * Returns whether any coordinate of the supplied vector is {@code NaN}.
     */
    public static boolean hasNaN(Vector3dc v) {
        return Double.isNaN(v.x()) || Double.isNaN(v.y()) || Double.isNaN(v.z());
    }

    /**
     * Returns whether any coordinate or orientation angle of the location is {@code NaN}.
     */
    public static boolean hasNaN(Location3fc l) {
        return Float.isNaN(l.x()) || Float.isNaN(l.y()) || Float.isNaN(l.z()) ||
               Double.isNaN(l.pitch()) || Double.isNaN(l.yaw());
    }

    /**
     * Returns whether any coordinate or orientation angle of the location is {@code NaN}.
     */
    public static boolean hasNaN(Location3dc l) {
        return Double.isNaN(l.x()) || Double.isNaN(l.y()) || Double.isNaN(l.z()) ||
               Double.isNaN(l.pitch()) || Double.isNaN(l.yaw());
    }

    /**
     * Normalizes {@code v} in place when its squared length is positive and returns the
     * same vector. Zero-length vectors remain unchanged, avoiding the undefined division
     * that ordinary normalization would perform.
     */
    public static Vector3f normalizeIfNotZero(Vector3f v) {
        // Preserve zero vectors because normalizing them divides by zero and would replace
        // their coordinates with NaN values.
        return v.lengthSquared() > 0 ? v.normalize(v) : v;
    }

    /**
     * Normalizes {@code v} in place when its squared length is positive and returns the
     * same vector. Zero-length vectors remain unchanged, avoiding the undefined division
     * that ordinary normalization would perform.
     */
    public static Vector3d normalizeIfNotZero(Vector3d v) {
        // Preserve zero vectors because normalizing them divides by zero and would replace
        // their coordinates with NaN values.
        return v.lengthSquared() > 0 ? v.normalize(v) : v;
    }

    /**
     * Expands the supplied bounding box in place and returns that same box. Each
     * {@code growth} component is subtracted from its minimum and added to its maximum;
     * negative values shrink that axis.
     */
    public static AABBd grow(AABBd aabb, Vector3dc growth) {
        aabb.minX -= growth.x();
        aabb.minY -= growth.y();
        aabb.minZ -= growth.z();
        aabb.maxX += growth.x();
        aabb.maxY += growth.y();
        aabb.maxZ += growth.z();
        return aabb;
    }

    /**
     * Expands the supplied bounding box in place and returns that same box. The selected
     * {@code blockFace} chooses one boundary to move by {@code growth}; negative growth
     * moves that boundary inward instead.
     */
    public static AABBd grow(AABBd aabb, BlockFace blockFace, double growth) {
        switch (blockFace) {
            case DOWN -> aabb.minY -= growth;
            case UP -> aabb.maxY += growth;
            case NORTH -> aabb.minZ -= growth;
            case SOUTH -> aabb.maxZ += growth;
            case WEST -> aabb.minX -= growth;
            case EAST -> aabb.maxX += growth;
            default -> throw new IllegalArgumentException("Invalid block face: " + blockFace);
        }
        return aabb;
    }
}
