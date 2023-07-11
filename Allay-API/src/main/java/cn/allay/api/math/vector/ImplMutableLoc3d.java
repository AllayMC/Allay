/*
 * This file is part of Math, licensed under the MIT License (MIT).
 *
 * Copyright (c) Flow Powered <https://github.com/flow>
 * Copyright (c) SpongePowered <https://github.com/SpongePowered>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cn.allay.api.math.vector;

import cn.allay.api.math.GenericMath;
import cn.allay.api.world.World;

import java.util.Objects;

final class ImplMutableLoc3d implements MutableLoc3d {
    private double x;
    private double y;
    private double z;
    private double pitch;
    private double yaw;
    private double headYaw;
    private World world;

    public ImplMutableLoc3d(final Vec2d v, final double pitch, final double yaw, final double headYaw, final World world) {
        this(v, 0, pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final Vec2d v, final float z, final double pitch, final double yaw, final double headYaw, final World world) {
        this(v, (double) z, pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final Vec2d v, final double z, final double pitch, final double yaw, final double headYaw, final World world) {
        this(v.x(), v.y(), z, pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final Vec4d v, final double pitch, final double yaw, final double headYaw, final World world) {
        this(v.x(), v.y(), v.z(), pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final VecNd v, final double pitch, final double yaw, final double headYaw, final World world) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final float x, final float y, final float z, final double pitch, final double yaw, final double headYaw, final World world) {
        this((double) x, (double) y, (double) z, pitch, yaw, headYaw, world);
    }

    public ImplMutableLoc3d(final double x, final double y, final double z, final double pitch, final double yaw, final double headYaw, final World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.world = world;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public void setX(final double x) {
        this.x = x;
    }

    @Override
    public void setX(final float x) {
        setX((double) x);
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public void setY(final double y) {
        this.y = y;
    }

    @Override
    public void setY(final float y) {
        setY((double) y);
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public void setZ(final double z) {
        this.z = z;
    }

    @Override
    public void setZ(final float z) {
        setZ((double) z);
    }

    @Override
    public double pitch() {
        return this.pitch;
    }

    @Override
    public void setPitch(final double pitch) {
        this.pitch = pitch;
    }

    @Override
    public double yaw() {
        return this.yaw;
    }

    @Override
    public void setYaw(final double yaw) {
        this.yaw = yaw;
    }

    @Override
    public double headYaw() {
        return this.headYaw;
    }

    @Override
    public void setHeadYaw(final double headYaw) {
        this.headYaw = headYaw;
    }

    @Override
    public World world() {
        return this.world;
    }

    @Override
    public void setWorld(final World world) {
        this.world = world;
    }

    @Override
    public void setLocation(final Loc3d location) {
        this.x = location.x();
        this.y = location.y();
        this.z = location.z();
        this.pitch = location.pitch();
        this.yaw = location.yaw();
        this.headYaw = location.headYaw();
        this.world = location.world();
    }

    @Override
    public void setPosition(final Pos3d position) {
        this.x = position.x();
        this.y = position.y();
        this.z = position.z();
        this.world = position.world();
    }

    @Override
    public void setVec3(final Vec3d vec3) {
        this.x = vec3.x();
        this.y = vec3.y();
        this.z = vec3.z();
    }

    @Override
    public int floorX() {
        return GenericMath.floor(this.x);
    }

    @Override
    public int floorY() {
        return GenericMath.floor(this.y);
    }

    @Override
    public int floorZ() {
        return GenericMath.floor(this.z);
    }

    @Override
    public MutableLoc3d add(final Vec3d v) {
        return this.add(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d add(final float x, final float y, final float z) {
        return this.add((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d add(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(this.x + x, this.y + y, this.z + z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d sub(final Vec3d v) {
        return this.sub(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d sub(final float x, final float y, final float z) {
        return this.sub((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d sub(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(this.x - x, this.y - y, this.z - z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d mul(final float a) {
        return this.mul((double) a);
    }

    @Override
    public MutableLoc3d mul(final double a) {
        return this.mul(a, a, a);
    }

    @Override
    public MutableLoc3d mul(final Vec3d v) {
        return this.mul(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d mul(final float x, final float y, final float z) {
        return this.mul((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d mul(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(this.x * x, this.y * y, this.z * z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d div(final float a) {
        return this.div((double) a);
    }

    @Override
    public MutableLoc3d div(final double a) {
        return this.div(a, a, a);
    }

    @Override
    public MutableLoc3d div(final Vec3d v) {
        return this.div(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d div(final float x, final float y, final float z) {
        return this.div((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d div(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(this.x / x, this.y / y, this.z / z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public double dot(final Vec3d v) {
        return this.dot(v.x(), v.y(), v.z());
    }

    @Override
    public double dot(final float x, final float y, final float z) {
        return this.dot((double) x, (double) y, (double) z);
    }

    @Override
    public double dot(final double x, final double y, final double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public MutableLoc3d project(final Vec3d v) {
        return this.project(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d project(final float x, final float y, final float z) {
        return this.project((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d project(final double x, final double y, final double z) {
        final double lengthSquared = x * x + y * y + z * z;
        if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = this.dot(x, y, z) / lengthSquared;
        return new ImplMutableLoc3d(a * x, a * y, a * z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d cross(final Vec3d v) {
        return this.cross(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d cross(final float x, final float y, final float z) {
        return this.cross((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d cross(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d pow(final float pow) {
        return this.pow((double) pow);
    }

    @Override
    public MutableLoc3d pow(final double power) {
        return new ImplMutableLoc3d(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d ceil() {
        return new ImplMutableLoc3d(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d floor() {
        return new ImplMutableLoc3d(GenericMath.floor(this.x), GenericMath.floor(this.y), GenericMath.floor(this.z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d round() {
        return new ImplMutableLoc3d(Math.round(this.x), Math.round(this.y), Math.round(this.z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d abs() {
        return new ImplMutableLoc3d(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d negate() {
        return new ImplMutableLoc3d(-this.x, -this.y, -this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d min(final Vec3d v) {
        return this.min(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d min(final float x, final float y, final float z) {
        return this.min((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d min(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d max(final Vec3d v) {
        return this.max(v.x(), v.y(), v.z());
    }

    @Override
    public MutableLoc3d max(final float x, final float y, final float z) {
        return this.max((double) x, (double) y, (double) z);
    }

    @Override
    public MutableLoc3d max(final double x, final double y, final double z) {
        return new ImplMutableLoc3d(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public double distanceSquared(final Vec3d v) {
        return this.distanceSquared(v.x(), v.y(), v.z());
    }

    @Override
    public double distanceSquared(final float x, final float y, final float z) {
        return this.distanceSquared((double) x, (double) y, (double) z);
    }

    @Override
    public double distanceSquared(final double x, final double y, final double z) {
        final double dx = this.x - x;
        final double dy = this.y - y;
        final double dz = this.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public double distance(final Vec3d v) {
        return this.distance(v.x(), v.y(), v.z());
    }

    @Override
    public double distance(final float x, final float y, final float z) {
        return this.distance((double) x, (double) y, (double) z);
    }

    @Override
    public double distance(final double x, final double y, final double z) {
        return (double) Math.sqrt(this.distanceSquared(x, y, z));
    }

    @Override
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public double length() {
        return (double) Math.sqrt(this.lengthSquared());
    }

    @Override
    public MutableLoc3d normalize() {
        final double length = this.length();
        if (Math.abs(length) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplMutableLoc3d(this.x / length, this.y / length, this.z / length, this.pitch, this.yaw, this.headYaw, this.world);
    }

    /**
     * Returns the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        return this.x < this.y ? (this.x < this.z ? 0 : 2) : (this.y < this.z ? 1 : 2);
    }

    /**
     * Returns the axis with the maximum value.
     *
     * @return {@link int} axis with maximum value
     */
    @Override
    public int maxAxis() {
        return this.x < this.y ? (this.y < this.z ? 2 : 1) : (this.x < this.z ? 2 : 0);
    }

    @Override
    public MutableVec2d toVec2() {
        return new ImplMutableVec2d(this);
    }

    @Override
    public MutableVec2d toVec2(final boolean useZ) {
        return new ImplMutableVec2d(this.x, useZ ? this.z : this.y);
    }

    @Override
    public MutableVec4d toVec4() {
        return this.toVec4(0);
    }

    @Override
    public MutableVec4d toVec4(final float w) {
        return this.toVec4((double) w);
    }

    @Override
    public MutableVec4d toVec4(final double w) {
        return new ImplMutableVec4d(this, w);
    }

    @Override
    public VecNd toVecN() {
        return new VecNd(this);
    }

    @Override
    public double[] toArray() {
        return new double[]{this.x, this.y, this.z};
    }

    @Override
    public MutableLoc3i toInt() {
        return new ImplMutableLoc3i(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3l toLong() {
        return new ImplMutableLoc3l(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3f toFloat() {
        return new ImplMutableLoc3f(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public MutableLoc3d toDouble() {
        return new ImplMutableLoc3d(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public int compareTo(final Vec3d v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Loc3d that)) return false;
        return Objects.equals(x, that.x()) &&
                Objects.equals(y, that.y()) &&
                Objects.equals(z, that.z()) &&
                Objects.equals(pitch, that.pitch()) &&
                Objects.equals(yaw, that.yaw()) &&
                Objects.equals(headYaw, that.headYaw()) &&
                Objects.equals(world, that.world());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, pitch, yaw, headYaw, world);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.pitch + ", " + this.yaw + ", " + this.headYaw + ", " + this.world + ")";
    }
}
