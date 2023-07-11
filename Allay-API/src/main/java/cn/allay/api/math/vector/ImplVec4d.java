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
record ImplVec4d(double x, double y, double z, double w) implements Vec4d {
    public ImplVec4d(final Vec2d v) {
        this(v, 0, 0);
    }

    public ImplVec4d(final Vec2d v, final float z, final float w) {
        this(v, (double) z, (double) w);
    }

    public ImplVec4d(final Vec2d v, final double z, final double w) {
        this(v.x(), v.y(), z, w);
    }

    public ImplVec4d(final Vec3d v) {
        this(v, 0);
    }

    public ImplVec4d(final Vec3d v, final float w) {
        this(v, (double) w);
    }

    public ImplVec4d(final Vec3d v, final double w) {
        this(v.x(), v.y(), v.z(), w);
    }

    public ImplVec4d(final VecNd v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, v.axisCount() > 3 ? v.get(3) : 0);
    }

    public ImplVec4d(final float x, final float y, final float z, final float w) {
        this((double) x, (double) y, (double) z, (double) w);
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
    public int floorW() {
        return GenericMath.floor(this.w);
    }

    @Override
    public ImplVec4d add(final Vec4d v) {
        return this.add(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d add(final float x, final float y, final float z, final float w) {
        return this.add((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d add(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    @Override
    public ImplVec4d sub(final Vec4d v) {
        return this.sub(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d sub(final float x, final float y, final float z, final float w) {
        return this.sub((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d sub(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    @Override
    public ImplVec4d mul(final float a) {
        return this.mul((double) a);
    }

    @Override
    public ImplVec4d mul(final double a) {
        return this.mul(a, a, a, a);
    }

    @Override
    public ImplVec4d mul(final Vec4d v) {
        return this.mul(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d mul(final float x, final float y, final float z, final float w) {
        return this.mul((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d mul(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(this.x * x, this.y * y, this.z * z, this.w * w);
    }

    @Override
    public ImplVec4d div(final float a) {
        return this.div((double) a);
    }

    @Override
    public ImplVec4d div(final double a) {
        return this.div(a, a, a, a);
    }

    @Override
    public ImplVec4d div(final Vec4d v) {
        return this.div(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d div(final float x, final float y, final float z, final float w) {
        return this.div((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d div(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(this.x / x, this.y / y, this.z / z, this.w / w);
    }

    @Override
    public double dot(final Vec4d v) {
        return this.dot(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double dot(final float x, final float y, final float z, final float w) {
        return this.dot((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public double dot(final double x, final double y, final double z, final double w) {
        return this.x * x + this.y * y + this.z * z + this.w * w;
    }

    @Override
    public ImplVec4d project(final Vec4d v) {
        return this.project(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d project(final float x, final float y, final float z, final float w) {
        return this.project((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d project(final double x, final double y, final double z, final double w) {
        final double lengthSquared = x * x + y * y + z * z + w * w;
        if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = this.dot(x, y, z, w) / lengthSquared;
        return new ImplVec4d(a * x, a * y, a * z, a * w);
    }

    @Override
    public ImplVec4d pow(final float pow) {
        return this.pow((double) pow);
    }

    @Override
    public ImplVec4d pow(final double power) {
        return new ImplVec4d(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
    }

    @Override
    public ImplVec4d ceil() {
        return new ImplVec4d(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), Math.ceil(this.w));
    }

    @Override
    public ImplVec4d floor() {
        return new ImplVec4d(GenericMath.floor(this.x), GenericMath.floor(this.y), GenericMath.floor(this.z), GenericMath.floor(this.w));
    }

    @Override
    public ImplVec4d round() {
        return new ImplVec4d(Math.round(this.x), Math.round(this.y), Math.round(this.z), Math.round(this.w));
    }

    @Override
    public ImplVec4d abs() {
        return new ImplVec4d(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
    }

    @Override
    public ImplVec4d negate() {
        return new ImplVec4d(-this.x, -this.y, -this.z, -this.w);
    }

    @Override
    public ImplVec4d min(final Vec4d v) {
        return this.min(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d min(final float x, final float y, final float z, final float w) {
        return this.min((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d min(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
    }

    @Override
    public ImplVec4d max(final Vec4d v) {
        return this.max(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public ImplVec4d max(final float x, final float y, final float z, final float w) {
        return this.max((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public ImplVec4d max(final double x, final double y, final double z, final double w) {
        return new ImplVec4d(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
    }

    @Override
    public double distanceSquared(final Vec4d v) {
        return this.distanceSquared(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double distanceSquared(final float x, final float y, final float z, final float w) {
        return this.distanceSquared((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public double distanceSquared(final double x, final double y, final double z, final double w) {
        final double dx = this.x - x;
        final double dy = this.y - y;
        final double dz = this.z - z;
        final double dw = this.w - w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    @Override
    public double distance(final Vec4d v) {
        return this.distance(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double distance(final float x, final float y, final float z, final float w) {
        return this.distance((double) x, (double) y, (double) z, (double) w);
    }

    @Override
    public double distance(final double x, final double y, final double z, final double w) {
        return (double) Math.sqrt(this.distanceSquared(x, y, z, w));
    }

    @Override
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    @Override
    public double length() {
        return (double) Math.sqrt(this.lengthSquared());
    }

    @Override
    public ImplVec4d normalize() {
        final double length = this.length();
        if (Math.abs(length) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplVec4d(this.x / length, this.y / length, this.z / length, this.w / length);
    }

    /**
     * Return the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        double value = this.x;
        int axis = 0;
        if (this.y < value) {
            value = this.y;
            axis = 1;
        }
        if (this.z < value) {
            value = this.z;
            axis = 2;
        }
        if (this.w < value) {
            axis = 3;
        }
        return axis;
    }

    /**
     * Return the axis with the maximum value.
     *
     * @return {@link int} axis with maximum value
     */
    @Override
    public int maxAxis() {
        double value = this.x;
        int axis = 0;
        if (this.y > value) {
            value = this.y;
            axis = 1;
        }
        if (this.z > value) {
            value = this.z;
            axis = 2;
        }
        if (this.w > value) {
            axis = 3;
        }
        return axis;
    }

    @Override
    public Vec2d toVec2() {
        return new ImplVec2d(this);
    }

    @Override
    public Vec3d toVec3() {
        return new ImplVec3d(this);
    }

    @Override
    public VecNd toVecN() {
        return new VecNd(this);
    }

    @Override
    public double[] toArray() {
        return new double[]{this.x, this.y, this.z, this.w};
    }

    @Override
    public ImplVec4i toInt() {
        return new ImplVec4i(this.x, this.y, this.z, this.w);
    }

    @Override
    public ImplVec4l toLong() {
        return new ImplVec4l(this.x, this.y, this.z, this.w);
    }

    @Override
    public ImplVec4f toFloat() {
        return new ImplVec4f(this.x, this.y, this.z, this.w);
    }

    @Override
    public ImplVec4d toDouble() {
        return new ImplVec4d(this.x, this.y, this.z, this.w);
    }

    @Override
    public int compareTo(final Vec4d v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec4d)) {
            return false;
        }
        final Vec4d that = (Vec4d) other;
        if (Double.compare(that.w(), this.w) != 0) {
            return false;
        }
        if (Double.compare(that.x(), this.x) != 0) {
            return false;
        }
        if (Double.compare(that.y(), this.y) != 0) {
            return false;
        }
        if (Double.compare(that.z(), this.z) != 0) {
            return false;
        }
        return true;
    }
}
