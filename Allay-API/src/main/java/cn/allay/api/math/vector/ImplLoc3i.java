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

record ImplLoc3i(int x, int y, int z, double pitch, double yaw, double headYaw, World world) implements Loc3i {
    public ImplLoc3i(final Vec2i v, final double pitch, final double yaw, final double headYaw, World world) {
        this(v, 0, pitch, yaw, headYaw, world);
    }

    public ImplLoc3i(final Vec2i v, final double z, final double pitch, final double yaw, final double headYaw, World world) {
        this(v, GenericMath.floor(z), pitch, yaw, headYaw, world);
    }

    public ImplLoc3i(final Vec2i v, final int z, final double pitch, final double yaw, final double headYaw, World world) {
        this(v.x(), v.y(), z, pitch, yaw, headYaw, world);
    }

    public ImplLoc3i(final Vec4i v, final double pitch, final double yaw, final double headYaw, World world) {
        this(v.x(), v.y(), v.z(), pitch, yaw, headYaw, world);
    }

    public ImplLoc3i(final VecNi v, final double pitch, final double yaw, final double headYaw, World world) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, pitch, yaw, headYaw, world);
    }

    public ImplLoc3i(final double x, final double y, final double z, final double pitch, final double yaw, final double headYaw, World world) {
        this(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), pitch, yaw, headYaw, world);
    }

    @Override
    public Loc3i add(final Vec3i v) {
        return this.add(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i add(final double x, final double y, final double z) {
        return this.add(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i add(final int x, final int y, final int z) {
        return new ImplLoc3i(this.x + x, this.y + y, this.z + z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i sub(final Vec3i v) {
        return this.sub(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i sub(final double x, final double y, final double z) {
        return this.sub(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i sub(final int x, final int y, final int z) {
        return new ImplLoc3i(this.x - x, this.y - y, this.z - z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i mul(final double a) {
        return this.mul(GenericMath.floor(a));
    }

    @Override
    public Loc3i mul(final int a) {
        return this.mul(a, a, a);
    }

    @Override
    public Loc3i mul(final Vec3i v) {
        return this.mul(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i mul(final double x, final double y, final double z) {
        return this.mul(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i mul(final int x, final int y, final int z) {
        return new ImplLoc3i(this.x * x, this.y * y, this.z * z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i div(final double a) {
        return this.div(GenericMath.floor(a));
    }

    @Override
    public Loc3i div(final int a) {
        return this.div(a, a, a);
    }

    @Override
    public Loc3i div(final Vec3i v) {
        return this.div(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i div(final double x, final double y, final double z) {
        return this.div(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i div(final int x, final int y, final int z) {
        return new ImplLoc3i(this.x / x, this.y / y, this.z / z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public int dot(final Vec3i v) {
        return this.dot(v.x(), v.y(), v.z());
    }

    @Override
    public int dot(final double x, final double y, final double z) {
        return this.dot(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public int dot(final int x, final int y, final int z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public Loc3i project(final Vec3i v) {
        return this.project(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i project(final double x, final double y, final double z) {
        return this.project(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i project(final int x, final int y, final int z) {
        final int lengthSquared = x * x + y * y + z * z;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = (float) this.dot(x, y, z) / lengthSquared;
        return new ImplLoc3i(a * x, a * y, a * z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i cross(final Vec3i v) {
        return this.cross(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i cross(final double x, final double y, final double z) {
        return this.cross(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i cross(final int x, final int y, final int z) {
        return new ImplLoc3i(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i pow(final double pow) {
        return this.pow(GenericMath.floor(pow));
    }

    @Override
    public Loc3i pow(final int power) {
        return new ImplLoc3i(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i abs() {
        return new ImplLoc3i(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i negate() {
        return new ImplLoc3i(-this.x, -this.y, -this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i min(final Vec3i v) {
        return this.min(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i min(final double x, final double y, final double z) {
        return this.min(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i min(final int x, final int y, final int z) {
        return new ImplLoc3i(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3i max(final Vec3i v) {
        return this.max(v.x(), v.y(), v.z());
    }

    @Override
    public Loc3i max(final double x, final double y, final double z) {
        return this.max(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public Loc3i max(final int x, final int y, final int z) {
        return new ImplLoc3i(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public int distanceSquared(final Vec3i v) {
        return this.distanceSquared(v.x(), v.y(), v.z());
    }

    @Override
    public int distanceSquared(final double x, final double y, final double z) {
        return this.distanceSquared(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public int distanceSquared(final int x, final int y, final int z) {
        final int dx = this.x - x;
        final int dy = this.y - y;
        final int dz = this.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public float distance(final Vec3i v) {
        return this.distance(v.x(), v.y(), v.z());
    }

    @Override
    public float distance(final double x, final double y, final double z) {
        return this.distance(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
    }

    @Override
    public float distance(final int x, final int y, final int z) {
        return (float) Math.sqrt(this.distanceSquared(x, y, z));
    }

    @Override
    public int lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
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
    public Vec2i toVec2() {
        return new ImplVec2i(this.x, this.y);
    }

    @Override
    public Vec2i toVec2(final boolean useZ) {
        return new ImplVec2i(this.x, useZ ? this.z : this.y);
    }

    @Override
    public Vec4i toVec4() {
        return this.toVec4(0);
    }

    @Override
    public Vec4i toVec4(final double w) {
        return this.toVec4(GenericMath.floor(w));
    }

    @Override
    public Vec4i toVec4(final int w) {
        return new ImplVec4i(this, w);
    }

    @Override
    public VecNi toVecN() {
        return new VecNi(this);
    }

    @Override
    public int[] toArray() {
        return new int[]{this.x, this.y, this.z};
    }

    @Override
    public Loc3i toInt() {
        return new ImplLoc3i(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3l toLong() {
        return new ImplLoc3l(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3f toFloat() {
        return new ImplLoc3f(this.x, this.y, this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public Loc3d toDouble() {
        return new ImplLoc3d((double) this.x, (double) this.y, (double) this.z, this.pitch, this.yaw, this.headYaw, this.world);
    }

    @Override
    public int compareTo(final Vec3i v) {
        return Integer.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Loc3i that)) return false;
        return Objects.equals(x, that.x()) &&
                Objects.equals(y, that.y()) &&
                Objects.equals(z, that.z()) &&
                Objects.equals(pitch, that.pitch()) &&
                Objects.equals(yaw, that.yaw()) &&
                Objects.equals(headYaw, that.headYaw()) &&
                Objects.equals(world, that.world());
    }
}
