package cn.allay.math.interfaces;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface IVector extends Cloneable {
    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2+z^2).
     *
     * @return the double
     */
    double length();

    /**
     * Gets the magnitude of the vector squared.
     *
     * @return the double
     */
    double lengthSquared();
}
