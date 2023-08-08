package cn.allay.api.utils;

import lombok.experimental.UtilityClass;
import org.joml.Vector3dc;
import org.joml.Vector3i;

/**
 * Allay Project 7/31/2023
 *
 * @author Cool_Loong
 */
@UtilityClass
public class MathUtils {
    public Vector3i floor(Vector3dc vector3d) {
        return new Vector3i((int) Math.floor(vector3d.x()), (int) Math.floor(vector3d.y()), (int) Math.floor(vector3d.z()));
    }

    public Vector3i ceil(Vector3dc vector3d) {
        return new Vector3i((int) Math.ceil(vector3d.x()), (int) Math.ceil(vector3d.y()), (int) Math.ceil(vector3d.z()));
    }

    public float fastFloatInverseSqrtF(float x) {
        float xHalf = 0.5f * x;
        int reEncode = Float.floatToIntBits(x);
        reEncode = 0x5f3759df - (reEncode >> 1);
        x = Float.intBitsToFloat(reEncode);
        x *= (1.5f - xHalf * x * x);
        return x;
    }

    public double fastDoubleInverseSqrtD(double x) {
        double xHalf = 0.5d * x;
        long reEncode = Double.doubleToLongBits(x);
        reEncode = 0x5fe6ec85e7de30daL - (reEncode >> 1);
        x = Double.longBitsToDouble(reEncode);
        x *= (1.5d - xHalf * x * x);
        return x;
    }
}
