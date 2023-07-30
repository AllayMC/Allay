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
record ImplVec3l(long x, long y, long z) implements Vec3l {
    public ImplVec3l(final Vec2l v) {
        this(v, 0);
    }

    public ImplVec3l(final Vec2l v, final double z) {
        this(v, GenericMath.floorl(z));
    }

    public ImplVec3l(final Vec2l v, final long z) {
        this(v.x(), v.y(), z);
    }

    public ImplVec3l(final Vec4l v) {
        this(v.x(), v.y(), v.z());
    }

    public ImplVec3l(final VecNl v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0);
    }

    public ImplVec3l(final double x, final double y, final double z) {
        this(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l add(final Vec3l v) {
        return this.add(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l add(final double x, final double y, final double z) {
        return this.add(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l add(final long x, final long y, final long z) {
        return new ImplVec3l(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vec3l sub(final Vec3l v) {
        return this.sub(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l sub(final double x, final double y, final double z) {
        return this.sub(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l sub(final long x, final long y, final long z) {
        return new ImplVec3l(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public Vec3l mul(final double a) {
        return this.mul(GenericMath.floorl(a));
    }

    @Override
    public Vec3l mul(final long a) {
        return this.mul(a, a, a);
    }

    @Override
    public Vec3l mul(final Vec3l v) {
        return this.mul(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l mul(final double x, final double y, final double z) {
        return this.mul(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l mul(final long x, final long y, final long z) {
        return new ImplVec3l(this.x * x, this.y * y, this.z * z);
    }

    @Override
    public Vec3l div(final double a) {
        return this.div(GenericMath.floorl(a));
    }

    @Override
    public Vec3l div(final long a) {
        return this.div(a, a, a);
    }

    @Override
    public Vec3l div(final Vec3l v) {
        return this.div(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l div(final double x, final double y, final double z) {
        return this.div(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l div(final long x, final long y, final long z) {
        return new ImplVec3l(this.x / x, this.y / y, this.z / z);
    }

    @Override
    public long dot(final Vec3l v) {
        return this.dot(v.x(), v.y(), v.z());
    }

    @Override
    public long dot(final double x, final double y, final double z) {
        return this.dot(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public long dot(final long x, final long y, final long z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public Vec3l project(final Vec3l v) {
        return this.project(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l project(final double x, final double y, final double z) {
        return this.project(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l project(final long x, final long y, final long z) {
        final long lengthSquared = x * x + y * y + z * z;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = (double) this.dot(x, y, z) / lengthSquared;
        return new ImplVec3l(a * x, a * y, a * z);
    }

    @Override
    public Vec3l cross(final Vec3l v) {
        return this.cross(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l cross(final double x, final double y, final double z) {
        return this.cross(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l cross(final long x, final long y, final long z) {
        return new ImplVec3l(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public Vec3l pow(final double pow) {
        return this.pow(GenericMath.floorl(pow));
    }

    @Override
    public Vec3l pow(final long power) {
        return new ImplVec3l(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power));
    }

    @Override
    public Vec3l abs() {
        return new ImplVec3l(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    @Override
    public Vec3l negate() {
        return new ImplVec3l(-this.x, -this.y, -this.z);
    }

    @Override
    public Vec3l min(final Vec3l v) {
        return this.min(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l min(final double x, final double y, final double z) {
        return this.min(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l min(final long x, final long y, final long z) {
        return new ImplVec3l(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
    }

    @Override
    public Vec3l max(final Vec3l v) {
        return this.max(v.x(), v.y(), v.z());
    }

    @Override
    public Vec3l max(final double x, final double y, final double z) {
        return this.max(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public Vec3l max(final long x, final long y, final long z) {
        return new ImplVec3l(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
    }

    @Override
    public long distanceSquared(final Vec3l v) {
        return this.distanceSquared(v.x(), v.y(), v.z());
    }

    @Override
    public long distanceSquared(final double x, final double y, final double z) {
        return this.distanceSquared(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public long distanceSquared(final long x, final long y, final long z) {
        final long dx = this.x - x;
        final long dy = this.y - y;
        final long dz = this.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public double distance(final Vec3l v) {
        return this.distance(v.x(), v.y(), v.z());
    }

    @Override
    public double distance(final double x, final double y, final double z) {
        return this.distance(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z));
    }

    @Override
    public double distance(final long x, final long y, final long z) {
        return Math.sqrt(this.distanceSquared(x, y, z));
    }

    @Override
    public long lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public double length() {
        return Math.sqrt(this.lengthSquared());
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
    public Vec2l toVec2() {
        return new ImplVec2l(this);
    }

    @Override
    public Vec2l toVec2(final boolean useZ) {
        return new ImplVec2l(this.x, useZ ? this.z : this.y);
    }

    @Override
    public Vec4l toVec4() {
        return this.toVec4(0);
    }

    @Override
    public Vec4l toVec4(final double w) {
        return this.toVec4(GenericMath.floorl(w));
    }

    @Override
    public Vec4l toVec4(final long w) {
        return new ImplVec4l(this, w);
    }

    @Override
    public VecNl toVecN() {
        return new VecNl(this);
    }

    @Override
    public long[] toArray() {
        return new long[]{this.x, this.y, this.z};
    }

    @Override
    public Vec3i toInt() {
        return new ImplVec3i(this.x, this.y, this.z);
    }

    @Override
    public Vec3l toLong() {
        return new ImplVec3l(this.x, this.y, this.z);
    }

    @Override
    public Vec3f toFloat() {
        return new ImplVec3f(this.x, this.y, this.z);
    }

    @Override
    public Vec3d toDouble() {
        return new ImplVec3d((double) this.x, (double) this.y, (double) this.z);
    }

    @Override
    public int compareTo(final Vec3l v) {
        return Long.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec3l that)) {
            return false;
        }
        if (that.x() != this.x) {
            return false;
        }
        if (that.y() != this.y) {
            return false;
        }
        return that.z() == this.z;
    }
}
