package cn.allay.math.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface IVector2 extends IVector {
    /**
     * Gets the X component.
     *
     * @return the x
     */
    double getX();

    /**
     * Gets the Z component.
     *
     * @return the z
     */
    double getZ();

    /**
     * Set the X component.
     */
    void setX(double x);

    /**
     * Set the Z component.
     */
    void setZ(double z);

    /**
     * Gets the floored value of the X component, indicating the block that this vector is contained with.
     *
     * @return the block x
     */
    default int getBlockX() {
        return (int) Math.floor(getX());
    }

    /**
     * Gets the floored value of the Z component, indicating the block that this vector is contained with.
     *
     * @return the block z
     */
    default int getBlockZ() {
        return (int) Math.floor(getZ());
    }

    /**
     * Adds a vector to this one
     *
     * @param vector2 the vector 2
     * @return the vector 2
     */
    default @NotNull IVector2 add(@NotNull IVector2 vector2) {
        this.setX(this.getX() + vector2.getX());
        this.setZ(this.getZ() + vector2.getZ());
        return this;
    }

    /**
     * Divides the vector by another.
     *
     * @param vector2 the vec
     * @return the vector 2
     */
    default @NotNull IVector2 divide(@NotNull IVector2 vector2) {
        this.setX(this.getX() / vector2.getX());
        this.setZ(this.getZ() / vector2.getZ());
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a scalar.
     *
     * @param m the m
     * @return the vector 2
     */
    default @NotNull IVector2 multiply(double m) {
        this.setX(this.getX() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * @see #multiply(double)
     */
    default @NotNull IVector2 multiply(float m) {
        this.setX(this.getX() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * @see #multiply(double)
     */
    default @NotNull IVector2 multiply(int m) {
        this.setX(this.getX() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * Multiplies the vector by another.
     *
     * @param vec the vec
     * @return the vector 2
     */
    default @NotNull IVector2 multiply(@NotNull IVector2 vec) {
        this.setX(this.getX() * vec.getX());
        this.setZ(this.getZ() * vec.getZ());
        return this;
    }

    /**
     * Angle float.
     *
     * @param other the other
     * @return the float
     */
    default float angle(@NotNull IVector2 other) {
        return (float) Math.acos(Math.max(-1.0, Math.min(1.0, this.dot(other) / (this.length() * other.length()))));
    }

    /**
     * Get the distance between this vector and another.
     *
     * @param other the other
     * @return the double
     */
    default double distance(@NotNull IVector2 other) {
        return distance(other.getX(), other.getZ());
    }

    default double distance(double x, double z) {
        return Math.sqrt(this.distanceSquared(x, z));
    }

    /**
     * Get the squared distance between this vector and another.
     */
    default double distanceSquared(double x, double z) {
        double ex = this.getX() - x;
        double ez = this.getZ() - z;
        return ez * ez + ex * ex;
    }

    /**
     * Get the squared distance between this vector and another.
     *
     * @param other the other
     * @return the double
     */
    default double distanceSquared(@NotNull IVector2 other) {
        return distanceSquared(other.getX(), other.getZ());
    }

    /**
     * Calculates the dot product of this vector with another.
     *
     * @param other the other
     * @return the double
     */
    default double dot(@NotNull IVector2 other) {
        return getX() * other.getX() + getZ() * other.getZ();
    }


    @Override
    default double length() {
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    default double lengthSquared() {
        return this.getX() * this.getX() + this.getZ() * this.getZ();
    }
}
