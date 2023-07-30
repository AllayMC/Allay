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

final class ImplMutableVec4d implements MutableVec4d {
    private double x;
    private double y;
    private double z;
    private double w;

    public ImplMutableVec4d(final Vec2d v) {
        this(v, 0, 0);
    }

    public ImplMutableVec4d(final Vec2d v, final float z, final float w) {
        this(v, z, (double) w);
    }

    public ImplMutableVec4d(final Vec2d v, final double z, final double w) {
        this(v.x(), v.y(), z, w);
    }

    public ImplMutableVec4d(final Vec3d v) {
        this(v, 0);
    }

    public ImplMutableVec4d(final Vec3d v, final float w) {
        this(v, (double) w);
    }

    public ImplMutableVec4d(final Vec3d v, final double w) {
        this(v.x(), v.y(), v.z(), w);
    }

    public ImplMutableVec4d(final VecNd v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, v.axisCount() > 3 ? v.get(3) : 0);
    }

    public ImplMutableVec4d(final float x, final float y, final float z, final float w) {
        this(x, y, z, (double) w);
    }

    public ImplMutableVec4d(final double x, final double y, final double z, final double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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
    public double w() {
        return this.w;
    }

    @Override
    public void setW(final double w) {
        this.w = w;
    }

    @Override
    public void setW(final float w) {
        setW((double) w);
    }

    @Override
    public void setVec4(final Vec4d vec4) {
        this.x = vec4.x();
        this.y = vec4.y();
        this.z = vec4.z();
        this.w = vec4.w();
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
    public MutableVec4d add(final Vec4d v) {
        return this.add(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d add(final float x, final float y, final float z, final float w) {
        return this.add(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d add(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    @Override
    public MutableVec4d sub(final Vec4d v) {
        return this.sub(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d sub(final float x, final float y, final float z, final float w) {
        return this.sub(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d sub(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    @Override
    public MutableVec4d mul(final float a) {
        return this.mul((double) a);
    }

    @Override
    public MutableVec4d mul(final double a) {
        return this.mul(a, a, a, a);
    }

    @Override
    public MutableVec4d mul(final Vec4d v) {
        return this.mul(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d mul(final float x, final float y, final float z, final float w) {
        return this.mul(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d mul(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(this.x * x, this.y * y, this.z * z, this.w * w);
    }

    @Override
    public MutableVec4d div(final float a) {
        return this.div((double) a);
    }

    @Override
    public MutableVec4d div(final double a) {
        return this.div(a, a, a, a);
    }

    @Override
    public MutableVec4d div(final Vec4d v) {
        return this.div(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d div(final float x, final float y, final float z, final float w) {
        return this.div(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d div(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(this.x / x, this.y / y, this.z / z, this.w / w);
    }

    @Override
    public double dot(final Vec4d v) {
        return this.dot(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double dot(final float x, final float y, final float z, final float w) {
        return this.dot(x, y, z, (double) w);
    }

    @Override
    public double dot(final double x, final double y, final double z, final double w) {
        return this.x * x + this.y * y + this.z * z + this.w * w;
    }

    @Override
    public MutableVec4d project(final Vec4d v) {
        return this.project(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d project(final float x, final float y, final float z, final float w) {
        return this.project(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d project(final double x, final double y, final double z, final double w) {
        final double lengthSquared = x * x + y * y + z * z + w * w;
        if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = this.dot(x, y, z, w) / lengthSquared;
        return new ImplMutableVec4d(a * x, a * y, a * z, a * w);
    }

    @Override
    public MutableVec4d pow(final float pow) {
        return this.pow((double) pow);
    }

    @Override
    public MutableVec4d pow(final double power) {
        return new ImplMutableVec4d(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
    }

    @Override
    public MutableVec4d ceil() {
        return new ImplMutableVec4d(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), Math.ceil(this.w));
    }

    @Override
    public MutableVec4d floor() {
        return new ImplMutableVec4d(GenericMath.floor(this.x), GenericMath.floor(this.y), GenericMath.floor(this.z), GenericMath.floor(this.w));
    }

    @Override
    public MutableVec4d round() {
        return new ImplMutableVec4d(Math.round(this.x), Math.round(this.y), Math.round(this.z), Math.round(this.w));
    }

    @Override
    public MutableVec4d abs() {
        return new ImplMutableVec4d(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
    }

    @Override
    public MutableVec4d negate() {
        return new ImplMutableVec4d(-this.x, -this.y, -this.z, -this.w);
    }

    @Override
    public MutableVec4d min(final Vec4d v) {
        return this.min(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d min(final float x, final float y, final float z, final float w) {
        return this.min(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d min(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
    }

    @Override
    public MutableVec4d max(final Vec4d v) {
        return this.max(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4d max(final float x, final float y, final float z, final float w) {
        return this.max(x, y, z, (double) w);
    }

    @Override
    public MutableVec4d max(final double x, final double y, final double z, final double w) {
        return new ImplMutableVec4d(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
    }

    @Override
    public double distanceSquared(final Vec4d v) {
        return this.distanceSquared(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double distanceSquared(final float x, final float y, final float z, final float w) {
        return this.distanceSquared(x, y, z, (double) w);
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
        return this.distance(x, y, z, (double) w);
    }

    @Override
    public double distance(final double x, final double y, final double z, final double w) {
        return Math.sqrt(this.distanceSquared(x, y, z, w));
    }

    @Override
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    @Override
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    public MutableVec4d normalize() {
        final double length = this.length();
        if (Math.abs(length) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplMutableVec4d(this.x / length, this.y / length, this.z / length, this.w / length);
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
    public MutableVec2d toVec2() {
        return new ImplMutableVec2d(this);
    }

    @Override
    public MutableVec3d toVec3() {
        return new ImplMutableVec3d(this);
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
    public MutableVec4i toInt() {
        return new ImplMutableVec4i(this.x, this.y, this.z, this.w);
    }

    @Override
    public MutableVec4l toLong() {
        return new ImplMutableVec4l(this.x, this.y, this.z, this.w);
    }

    @Override
    public MutableVec4f toFloat() {
        return new ImplMutableVec4f(this.x, this.y, this.z, this.w);
    }

    @Override
    public MutableVec4d toDouble() {
        return new ImplMutableVec4d(this.x, this.y, this.z, this.w);
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
        if (!(other instanceof Vec4d that)) {
            return false;
        }
        if (Double.compare(that.w(), this.w) != 0) {
            return false;
        }
        if (Double.compare(that.x(), this.x) != 0) {
            return false;
        }
        if (Double.compare(that.y(), this.y) != 0) {
            return false;
        }
        return Double.compare(that.z(), this.z) == 0;
    }

    @Override
    public int hashCode() {
        int result = (this.x != +0.0f ? Double.hashCode(this.x) : 0);
        result = 31 * result + (this.y != +0.0f ? Double.hashCode(this.y) : 0);
        result = 31 * result + (this.z != +0.0f ? Double.hashCode(this.z) : 0);
        return 31 * result + (this.w != +0.0f ? Double.hashCode(this.w) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
}
