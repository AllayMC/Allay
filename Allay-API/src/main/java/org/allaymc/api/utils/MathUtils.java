package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;
import org.joml.*;

import java.lang.Math;

import static java.lang.StrictMath.*;

/**
 * Allay Project 7/31/2023
 *
 * @author Cool_Loong
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

    public float fastFloatInverseSqrt(float x) {
        float xHalf = 0.5f * x;
        int reEncode = Float.floatToIntBits(x);
        reEncode = 0x5f3759df - (reEncode >> 1);
        x = Float.intBitsToFloat(reEncode);
        x *= (1.5f - xHalf * x * x);
        return x;
    }

    public double fastDoubleInverseSqrt(double x) {
        double xHalf = 0.5d * x;
        long reEncode = Double.doubleToLongBits(x);
        reEncode = 0x5fe6ec85e7de30daL - (reEncode >> 1);
        x = Double.longBitsToDouble(reEncode);
        x *= (1.5d - xHalf * x * x);
        return x;
    }

    public boolean isInRange(float l, float value, float r) {
        return l <= value && value <= r;
    }

    /**
     * 通过yaw与pitch计算出等价的Vector3方向向量
     *
     * @param yaw   yaw
     * @param pitch pitch
     *
     * @return Vector3方向向量
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
     * 通过方向向量计算出yaw
     * <p>
     * Calculate yaw from the direction vector
     *
     * @param vector 方向向量
     *
     * @return yaw
     */
    public double getYawFromVector(Vector3fc vector) {
        double length = vector.x() * vector.x() + vector.z() * vector.z();
        // 避免NAN
        if (length == 0) {
            return 0;
        }
        double yaw = toDegrees(asin(-vector.x() / sqrt(length)));
        return -vector.z() > 0.0D ? 180.0D - yaw : StrictMath.abs(yaw) < 1E-10 ? 0 : yaw;
    }

    /**
     * 通过方向向量计算出pitch
     * <p>
     * Calculate the pitch by the direction vector
     *
     * @param vector 方向向量
     *
     * @return pitch
     */
    public double getPitchFromVector(Vector3fc vector) {
        double length =
                vector.x() * vector.x() +
                        vector.z() * vector.z() +
                        vector.y() * vector.y();
        // 避免NAN
        if (length == 0) {
            return 0;
        }
        var pitch = toDegrees(asin(-vector.y() / sqrt(length)));
        return StrictMath.abs(pitch) < 1E-10 ? 0 : pitch;
    }
}
