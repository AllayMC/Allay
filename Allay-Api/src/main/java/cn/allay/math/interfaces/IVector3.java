package cn.allay.math.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Describes a three-dimensional vector
 */
public interface IVector3 extends IVector {
    /**
     * Gets the X component.
     *
     * @return the x
     */
    double getX();

    /**
     * Gets the Y component.
     *
     * @return the y
     */
    double getY();

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
     * Set the Y component.
     */
    void setY(double y);

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
     * Gets the floored value of the Y component, indicating the block that this vector is contained with.
     *
     * @return the block y
     */
    default int getBlockY() {
        return (int) Math.floor(getY());
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
     * @param vector3 the vector 2
     * @return the vector 2
     */
    default @NotNull IVector3 add(@NotNull IVector3 vector3) {
        this.setX(this.getX() + vector3.getX());
        this.setX(this.getY() + vector3.getY());
        this.setZ(this.getZ() + vector3.getZ());
        return this;
    }

    /**
     * Divides the vector by another.
     *
     * @param vector3 the vec
     * @return the vector 2
     */
    default @NotNull IVector3 divide(@NotNull IVector3 vector3) {
        this.setX(this.getX() / vector3.getX());
        this.setX(this.getY() / vector3.getY());
        this.setZ(this.getZ() / vector3.getZ());
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a scalar.
     *
     * @param m the m
     * @return the vector 2
     */
    default @NotNull IVector3 multiply(double m) {
        this.setX(this.getX() * m);
        this.setX(this.getY() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * @see #multiply(double)
     */
    default @NotNull IVector3 multiply(float m) {
        this.setX(this.getX() * m);
        this.setX(this.getY() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * @see #multiply(double)
     */
    default @NotNull IVector3 multiply(int m) {
        this.setX(this.getX() * m);
        this.setX(this.getY() * m);
        this.setZ(this.getZ() * m);
        return this;
    }

    /**
     * Multiplies the vector by another.
     *
     * @param vector3 the vec
     * @return the vector 2
     */
    default @NotNull IVector3 multiply(@NotNull IVector3 vector3) {
        this.setX(this.getX() * vector3.getX());
        this.setX(this.getY() * vector3.getY());
        this.setZ(this.getZ() * vector3.getZ());
        return this;
    }

    /**
     * Angle float.
     *
     * @param other the other
     * @return the float
     */
    default float angle(@NotNull IVector3 other) {
        return (float) Math.acos(Math.max(-1.0, Math.min(1.0, this.dot(other) / (this.length() * other.length()))));
    }

    /**
     * Get the distance between this vector and another.
     *
     * @param other the other
     * @return the double
     */
    default double distance(@NotNull IVector3 other) {
        return this.distance(other.getX(), other.getY(), other.getZ());
    }

    /**
     * Get the distance between this vector and another.
     *
     * @param other the other
     * @return the double
     */
    default double distance(double x, double y, double z) {
        return Math.sqrt(this.distanceSquared(x, y, z));
    }

    /**
     * Get the squared distance between this vector and another.
     *
     * @param other the other
     * @return the double
     */
    default double distanceSquared(@NotNull IVector3 other) {
        return this.distanceSquared(other.getX(), other.getY(), other.getZ());
    }

    /**
     * Get the squared distance between this vector and another.
     */
    default double distanceSquared(double x, double y, double z) {
        double ex = this.getX() - x;
        double ey = this.getY() - y;
        double ez = this.getZ() - z;
        return ez * ez + ey * ey + ex * ex;
    }

    /**
     * Calculates the dot product of this vector with another.
     *
     * @param other the other
     * @return the double
     */
    default double dot(@NotNull IVector3 other) {
        return getX() * other.getX() + getY() * other.getY() + getZ() * other.getZ();
    }

    @Override
    default double length() {
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    default double lengthSquared() {
        return this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ();
    }
}
