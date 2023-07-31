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
}
