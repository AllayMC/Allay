package cn.allay.math.vector.interfaces;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 *
 * Describes a three-dimensional vector
 */
public interface Vector3<T extends Number> extends Vector {
    T getX();
    T getY();
    T getZ();
}
