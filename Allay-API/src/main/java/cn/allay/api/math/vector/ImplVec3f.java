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
import com.google.errorprone.annotations.Immutable;

@Immutable
record ImplVec3f(float x, float y, float z) implements Vec3f {
    public ImplVec3f(final Vec2f v) {
        this(v, 0);
    }

    public ImplVec3f(final Vec2f v, final double z) {
        this(v, (float) z);
    }

    public ImplVec3f(final Vec2f v, final float z) {
        this(v.x(), v.y(), z);
    }

    public ImplVec3f(final Vec4f v) {
        this(v.x(), v.y(), v.z());
    }

    public ImplVec3f(final VecNf v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0);
    }

    public ImplVec3f(final double x, final double y, final double z) {
        this((float) x, (float) y, (float) z);
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
    public ImplVec3f add(final Vec3f v) {
        return this.add(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f add(final double x, final double y, final double z) {
        return this.add((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f add(final float x, final float y, final float z) {
        return new ImplVec3f(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public ImplVec3f sub(final Vec3f v) {
        return this.sub(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f sub(final double x, final double y, final double z) {
        return this.sub((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f sub(final float x, final float y, final float z) {
        return new ImplVec3f(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public ImplVec3f mul(final double a) {
        return this.mul((float) a);
    }

    @Override
    public ImplVec3f mul(final float a) {
        return this.mul(a, a, a);
    }

    @Override
    public ImplVec3f mul(final Vec3f v) {
        return this.mul(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f mul(final double x, final double y, final double z) {
        return this.mul((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f mul(final float x, final float y, final float z) {
        return new ImplVec3f(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public ImplVec3f div(final double a) {
        return this.div((float) a);
    }

    @Override
    public ImplVec3f div(final float a) {
        return this.div(a, a, a);
    }

    @Override
    public ImplVec3f div(final Vec3f v) {
        return this.div(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f div(final double x, final double y, final double z) {
        return this.div((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f div(final float x, final float y, final float z) {
        return new ImplVec3f(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public float dot(final Vec3f v) {
        return this.dot(v.x(), v.y(), v.z());
    }

    @Override
    public float dot(final double x, final double y, final double z) {
        return this.dot((float) x, (float) y, (float) z);
    }

    @Override
    public float dot(final float x, final float y, final float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public ImplVec3f project(final Vec3f v) {
        return this.project(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f project(final double x, final double y, final double z) {
        return this.project((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f project(final float x, final float y, final float z) {
        final float lengthSquared = x * x + y * y + z * z;
        if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = this.dot(x, y, z) / lengthSquared;
        return new ImplVec3f(a * x, a * y, a * z);
    }

    @Override
    public ImplVec3f cross(final Vec3f v) {
        return this.cross(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f cross(final double x, final double y, final double z) {
        return this.cross((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f cross(final float x, final float y, final float z) {
        return new ImplVec3f(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public ImplVec3f pow(final double pow) {
        return this.pow((float) pow);
    }

    @Override
    public ImplVec3f pow(final float power) {
        return new ImplVec3f(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power));
    }

    @Override
    public ImplVec3f ceil() {
        return new ImplVec3f(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
    }

    @Override
    public ImplVec3f floor() {
        return new ImplVec3f(GenericMath.floor(this.x), GenericMath.floor(this.y), GenericMath.floor(this.z));
    }

    @Override
    public ImplVec3f round() {
        return new ImplVec3f(Math.round(this.x), Math.round(this.y), Math.round(this.z));
    }

    @Override
    public ImplVec3f abs() {
        return new ImplVec3f(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    @Override
    public ImplVec3f negate() {
        return new ImplVec3f(-this.x, -this.y, -this.z);
    }

    @Override
    public ImplVec3f min(final Vec3f v) {
        return this.min(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f min(final double x, final double y, final double z) {
        return this.min((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f min(final float x, final float y, final float z) {
        return new ImplVec3f(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
    }

    @Override
    public ImplVec3f max(final Vec3f v) {
        return this.max(v.x(), v.y(), v.z());
    }

    @Override
    public ImplVec3f max(final double x, final double y, final double z) {
        return this.max((float) x, (float) y, (float) z);
    }

    @Override
    public ImplVec3f max(final float x, final float y, final float z) {
        return new ImplVec3f(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
    }

    @Override
    public float distanceSquared(final Vec3f v) {
        return this.distanceSquared(v.x(), v.y(), v.z());
    }

    @Override
    public float distanceSquared(final double x, final double y, final double z) {
        return this.distanceSquared((float) x, (float) y, (float) z);
    }

    @Override
    public float distanceSquared(final float x, final float y, final float z) {
        final float dx = this.x - x;
        final float dy = this.y - y;
        final float dz = this.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public float distance(final Vec3f v) {
        return this.distance(v.x(), v.y(), v.z());
    }

    @Override
    public float distance(final double x, final double y, final double z) {
        return this.distance((float) x, (float) y, (float) z);
    }

    @Override
    public float distance(final float x, final float y, final float z) {
        return (float) Math.sqrt(this.distanceSquared(x, y, z));
    }

    @Override
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    @Override
    public ImplVec3f normalize() {
        final float length = this.length();
        if (Math.abs(length) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplVec3f(this.x / length, this.y / length, this.z / length);
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
    public Vec2f toVec2() {
        return new ImplVec2f(this);
    }

    @Override
    public Vec2f toVec2(final boolean useZ) {
        return new ImplVec2f(this.x, useZ ? this.z : this.y);
    }

    @Override
    public Vec4f toVec4() {
        return this.toVec4(0);
    }

    @Override
    public Vec4f toVec4(final double w) {
        return this.toVec4((float) w);
    }

    @Override
    public Vec4f toVec4(final float w) {
        return new ImplVec4f(this, w);
    }

    @Override
    public VecNf toVecN() {
        return new VecNf(this);
    }

    @Override
    public float[] toArray() {
        return new float[]{this.x, this.y, this.z};
    }

    @Override
    public ImplVec3i toInt() {
        return new ImplVec3i(this.x, this.y, this.z);
    }

    @Override
    public ImplVec3l toLong() {
        return new ImplVec3l(this.x, this.y, this.z);
    }

    @Override
    public ImplVec3f toFloat() {
        return new ImplVec3f(this.x, this.y, this.z);
    }

    @Override
    public ImplVec3d toDouble() {
        return new ImplVec3d(this.x, this.y, this.z);
    }

    @Override
    public int compareTo(final Vec3f v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec3f that)) {
            return false;
        }
        if (Float.compare(that.x(), this.x) != 0) {
            return false;
        }
        if (Float.compare(that.y(), this.y) != 0) {
            return false;
        }
        return Float.compare(that.z(), this.z) == 0;
    }
}
