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
 * Math utilities.
 *
 * @author Cool_Loong | daoge_cmd
 */
@UtilityClass
public final class MathUtils {

    private static final double[] SIN_LOOK_UP_TABLE = new double[65536];

    static {
        for (int i = 0; i < 65536; i++) {
            SIN_LOOK_UP_TABLE[i] = Math.sin(i * Math.PI * 2.0D / 65536.0d);
        }
    }

    /**
     * Converts the double vector passed to float vector
     *
     * @param vec the double vector to convert
     * @return the float vector
     */
    public static Vector3fc toVec3f(Vector3dc vec) {
        return vec.get(new Vector3f());
    }

    /**
     * Floor a vector.
     *
     * @param vector3d the vector to floor
     * @return the floored vector
     */
    public static Vector3i floor(Vector3dc vector3d) {
        return new Vector3i((int) Math.floor(vector3d.x()), (int) Math.floor(vector3d.y()), (int) Math.floor(vector3d.z()));
    }

    /**
     * Floor a vector.
     *
     * @param vector3f the vector to floor
     * @return the floored vector
     */
    public static Vector3i floor(Vector3fc vector3f) {
        return new Vector3i((int) Math.floor(vector3f.x()), (int) Math.floor(vector3f.y()), (int) Math.floor(vector3f.z()));
    }

    /**
     * Ceil a vector.
     *
     * @param vector3d the vector to ceil
     * @return the ceiled vector
     */
    public static Vector3i ceil(Vector3dc vector3d) {
        return new Vector3i((int) Math.ceil(vector3d.x()), (int) Math.ceil(vector3d.y()), (int) Math.ceil(vector3d.z()));
    }

    /**
     * Ceil a vector.
     *
     * @param vector3f the vector to ceil
     * @return the ceiled vector
     */
    public static Vector3i ceil(Vector3fc vector3f) {
        return new Vector3i((int) Math.ceil(vector3f.x()), (int) Math.ceil(vector3f.y()), (int) Math.ceil(vector3f.z()));
    }

    /**
     * Center a vector (Adding 0.5 to the coordinates of the vector).
     *
     * @param vector3i the vector to center
     * @return the centered vector
     */
    public static Vector3d center(Vector3ic vector3i) {
        return new Vector3d(vector3i.x() + 0.5, vector3i.y() + 0.5, vector3i.z() + 0.5);
    }

    /**
     * Round a double to a certain precision.
     *
     * @param d         the double to round
     * @param precision the precision
     * @return the rounded double
     */
    public static double round(double d, int precision) {
        double pow = Math.pow(10, precision);
        return ((double) Math.round(d * pow)) / pow;
    }

    /**
     * Fast inverse square root (float).
     *
     * @param x the value
     * @return the result
     */
    public static float fastFloatInverseSqrt(float x) {
        float xHalf = 0.5f * x;
        int reEncode = Float.floatToIntBits(x);
        reEncode = 0x5f3759df - (reEncode >> 1);
        x = Float.intBitsToFloat(reEncode);
        x *= (1.5f - xHalf * x * x);
        return x;
    }

    /**
     * Fast inverse square root (double).
     *
     * @param x the value
     * @return the result
     */
    public static double fastDoubleInverseSqrt(double x) {
        double xHalf = 0.5d * x;
        long reEncode = Double.doubleToLongBits(x);
        reEncode = 0x5fe6ec85e7de30daL - (reEncode >> 1);
        x = Double.longBitsToDouble(reEncode);
        x *= (1.5d - xHalf * x * x);
        return x;
    }

    /**
     * Check if the value is in the range.
     *
     * @param l     the left
     * @param value the value to check
     * @param r     the right
     * @return {@code true} if the value is in the range, otherwise {@code false}.
     */
    public static boolean isInRange(double l, double value, double r) {
        return l <= value && value <= r;
    }

    /**
     * @see #getDirectionVector(double, double)
     */
    public static Vector3d getDirectionVector(Location3dc location) {
        return getDirectionVector(location.yaw(), location.pitch());
    }

    /**
     * Calculate equivalent normalized direction vector by yaw and pitch.
     *
     * @param yaw   the yaw
     * @param pitch the pitch
     * @return the direction vector
     */
    public static Vector3d getDirectionVector(double yaw, double pitch) {
        var pitch0 = toRadians(pitch + 90);
        var yaw0 = toRadians(yaw + 90);
        var x = sin(pitch0) * cos(yaw0);
        var z = sin(pitch0) * sin(yaw0);
        var y = cos(pitch0);
        return new Vector3d(x, y, z).normalize();
    }

    /**
     * Calculate yaw from the direction vector.
     *
     * @param vector the direction vector
     * @return the yaw
     */
    public static double getYawFromVector(Vector3dc vector) {
        double length = vector.x() * vector.x() + vector.z() * vector.z();
        // Prevent NAN
        if (length == 0) {
            return 0;
        }
        double yaw = toDegrees(asin(-vector.x() / sqrt(length)));
        return -vector.z() > 0.0D ? 180.0D - yaw : StrictMath.abs(yaw) < 1E-10 ? 0 : yaw;
    }

    /**
     * Calculate the pitch by the direction vector.
     *
     * @param vector the direction vector
     * @return the pitch
     */
    public static double getPitchFromVector(Vector3dc vector) {
        double length =
                vector.x() * vector.x() +
                vector.z() * vector.z() +
                vector.y() * vector.y();
        // Prevent NAN
        if (length == 0) {
            return 0;
        }
        var pitch = toDegrees(asin(-vector.y() / sqrt(length)));
        return StrictMath.abs(pitch) < 1E-10 ? 0 : pitch;
    }

    /**
     * Calculate sin value quickly by looking up a pre-calculated table.
     *
     * @param radian the radian
     * @return the sin value
     */
    public static double fastSin(float radian) {
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F) & 0xFFFF)];
    }

    /**
     * Calculate sin value quickly by looking up a pre-calculated table.
     *
     * @param radian the radian
     * @return the sin value
     */
    public static double fastSin(double radian) {
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F) & 0xFFFF)];
    }

    /**
     * Calculate cos value quickly by looking up a pre-calculated table.
     *
     * @param radian the radian
     * @return the cos value
     */
    public static double fastCos(float radian) {
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    /**
     * Calculate cos value quickly by looking up a pre-calculated table.
     *
     * @param radian the radian
     * @return the cos value
     */
    public static double fastCos(double radian) {
        return SIN_LOOK_UP_TABLE[((int) (radian * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    /**
     * Check if the vector contains NaN.
     *
     * @param v the vector to check
     * @return {@code true} if the vector contains NaN, {@code false} otherwise.
     */
    public static boolean hasNaN(Vector3fc v) {
        return Float.isNaN(v.x()) || Float.isNaN(v.y()) || Float.isNaN(v.z());
    }

    /**
     * Check if the vector contains NaN.
     *
     * @param v the vector to check
     * @return {@code true} if the vector contains NaN, {@code false} otherwise.
     */
    public static boolean hasNaN(Vector3dc v) {
        return Double.isNaN(v.x()) || Double.isNaN(v.y()) || Double.isNaN(v.z());
    }

    /**
     * Check if the location contains NaN.
     *
     * @param l the location to check
     * @return {@code true} if the vector contains NaN, {@code false} otherwise.
     */
    public static boolean hasNaN(Location3fc l) {
        return Float.isNaN(l.x()) || Float.isNaN(l.y()) || Float.isNaN(l.z()) ||
               Double.isNaN(l.pitch()) || Double.isNaN(l.yaw());
    }

    /**
     * Check if the location contains NaN.
     *
     * @param l the location to check
     * @return {@code true} if the vector contains NaN, {@code false} otherwise.
     */
    public static boolean hasNaN(Location3dc l) {
        return Double.isNaN(l.x()) || Double.isNaN(l.y()) || Double.isNaN(l.z()) ||
               Double.isNaN(l.pitch()) || Double.isNaN(l.yaw());
    }

    /**
     * Normalize the vector if it is not zero. If the vector is zero, it can't be normalized, otherwise
     * a vector with three NaN values will be produced.
     *
     * @param v the vector
     * @return the normalized vector
     */
    public static Vector3f normalizeIfNotZero(Vector3f v) {
        return v.lengthSquared() > 0 ? v.normalize(v) : v;
    }

    /**
     * Normalize the vector if it is not zero. If the vector is zero, it can't be normalized, otherwise
     * a vector with three NaN values will be produced.
     *
     * @param v the vector
     * @return the normalized vector
     */
    public static Vector3d normalizeIfNotZero(Vector3d v) {
        return v.lengthSquared() > 0 ? v.normalize(v) : v;
    }

    /**
     * Grows the AABB on all axes as represented by the vector passed. The
     * vec values are subtracted from the minimum values of the AABB and added
     * to maximum values of the AABB.
     *
     * @param aabb   the AABB to grow
     * @param growth the vector
     * @return the grown AABB
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
     * Grows the given axis-aligned bounding box (AABB) in the direction of the specified block face by a given growth value.
     *
     * @param aabb      the axis-aligned bounding box (AABB) to grow
     * @param blockFace the block face direction to grow towards (e.g., DOWN, UP, NORTH, SOUTH, WEST, EAST)
     * @param growth    the value by which to grow the AABB along the specified block face direction
     * @return the grown AABB
     * @throws IllegalArgumentException if the specified block face is invalid
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
