package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;
import org.joml.*;

import java.lang.Math;

import static java.lang.StrictMath.*;

/**
 * Math utilities.
 *
 * @author Cool_Loong | daoge_cmd
 */
@UtilityClass
public class MathUtils {

    public Vector3ic CBVecToJOMLVec(org.cloudburstmc.math.vector.Vector3i cbVec) {
        return new Vector3i(cbVec.getX(), cbVec.getY(), cbVec.getZ());
    }

    public org.cloudburstmc.math.vector.Vector3i JOMLVecToCBVec(Vector3ic JOMLVec) {
        return org.cloudburstmc.math.vector.Vector3i.from(JOMLVec.x(), JOMLVec.y(), JOMLVec.z());
    }

    public Vector3dc CBVecToJOMLVec(org.cloudburstmc.math.vector.Vector3d cbVec) {
        return new Vector3d(cbVec.getX(), cbVec.getY(), cbVec.getZ());
    }

    public org.cloudburstmc.math.vector.Vector3d JOMLVecToCBVec(Vector3dc JOMLVec) {
        return org.cloudburstmc.math.vector.Vector3d.from(JOMLVec.x(), JOMLVec.y(), JOMLVec.z());
    }

    public Vector3fc CBVecToJOMLVec(org.cloudburstmc.math.vector.Vector3f cbVec) {
        return new Vector3f(cbVec.getX(), cbVec.getY(), cbVec.getZ());
    }

    public org.cloudburstmc.math.vector.Vector3f JOMLVecToCBVec(Vector3fc JOMLVec) {
        return org.cloudburstmc.math.vector.Vector3f.from(JOMLVec.x(), JOMLVec.y(), JOMLVec.z());
    }

    public Vector3i floor(Vector3dc vector3d) {
        return new Vector3i((int) Math.floor(vector3d.x()), (int) Math.floor(vector3d.y()), (int) Math.floor(vector3d.z()));
    }

    public Vector3i floor(Vector3fc vector3f) {
        return new Vector3i((int) Math.floor(vector3f.x()), (int) Math.floor(vector3f.y()), (int) Math.floor(vector3f.z()));
    }

    public Vector3i ceil(Vector3dc vector3d) {
        return new Vector3i((int) Math.ceil(vector3d.x()), (int) Math.ceil(vector3d.y()), (int) Math.ceil(vector3d.z()));
    }

    public Vector3i ceil(Vector3fc vector3f) {
        return new Vector3i((int) Math.ceil(vector3f.x()), (int) Math.ceil(vector3f.y()), (int) Math.ceil(vector3f.z()));
    }

    public static double round(double d, int precision) {
        double pow = Math.pow(10, precision);
        return ((double) Math.round(d * pow)) / pow;
    }

    /**
     * Fast inverse square root (float).
     *
     * @param x value.
     *
     * @return result.
     */
    public float fastFloatInverseSqrt(float x) {
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
     * @param x value.
     *
     * @return result.
     */
    public double fastDoubleInverseSqrt(double x) {
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
     * @param l     left.
     * @param value value.
     * @param r     right.
     *
     * @return {@code true} if the value is in the range, otherwise {@code false}.
     */
    public boolean isInRange(float l, float value, float r) {
        return l <= value && value <= r;
    }

    /**
     * Calculate equivalent direction vector by yaw and pitch.
     *
     * @param yaw   yaw
     * @param pitch pitch
     *
     * @return direction vector.
     */
    public Vector3f getDirectionVector(double yaw, double pitch) {
        var pitch0 = toRadians(pitch + 90);
        var yaw0 = toRadians(yaw + 90);
        var x = sin(pitch0) * cos(yaw0);
        var z = sin(pitch0) * sin(yaw0);
        var y = cos(pitch0);
        return new Vector3f((float) x, (float) y, (float) z).normalize();
    }

    /**
     * Calculate yaw from the direction vector.
     *
     * @param vector direction vector.
     *
     * @return yaw.
     */
    public double getYawFromVector(Vector3fc vector) {
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
     * @param vector direction vector.
     *
     * @return pitch.
     */
    public double getPitchFromVector(Vector3fc vector) {
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
}
