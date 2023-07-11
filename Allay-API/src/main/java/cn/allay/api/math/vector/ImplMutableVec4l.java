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

final class ImplMutableVec4l implements MutableVec4l {
    private long x;
    private long y;
    private long z;
    private long w;

    public ImplMutableVec4l(final Vec2l v) {
        this(v, 0, 0);
    }

    public ImplMutableVec4l(final Vec2l v, final double z, final double w) {
        this(v, GenericMath.floorl(z), GenericMath.floorl(w));
    }

    public ImplMutableVec4l(final Vec2l v, final long z, final long w) {
        this(v.x(), v.y(), z, w);
    }

    public ImplMutableVec4l(final Vec3l v) {
        this(v, 0);
    }

    public ImplMutableVec4l(final Vec3l v, final double w) {
        this(v, GenericMath.floorl(w));
    }

    public ImplMutableVec4l(final Vec3l v, final long w) {
        this(v.x(), v.y(), v.z(), w);
    }

    public ImplMutableVec4l(final VecNl v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, v.axisCount() > 3 ? v.get(3) : 0);
    }

    public ImplMutableVec4l(final double x, final double y, final double z, final double w) {
        this(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    public ImplMutableVec4l(final long x, final long y, final long z, final long w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public long x() {
        return this.x;
    }

    @Override
    public void setX(final long x) {
        this.x = x;
    }

    @Override
    public long y() {
        return this.y;
    }

    @Override
    public void setY(final long y) {
        this.y = y;
    }

    @Override
    public long z() {
        return this.z;
    }

    @Override
    public void setZ(final long z) {
        this.z = z;
    }

    @Override
    public long w() {
        return this.w;
    }

    @Override
    public void setW(final long w) {
        this.w = w;
    }

    @Override
    public void setVec4(final Vec4l vec4) {
        this.x = vec4.x();
        this.y = vec4.y();
        this.z = vec4.z();
        this.w = vec4.w();
    }

    @Override
    public MutableVec4l add(final Vec4l v) {
        return this.add(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l add(final double x, final double y, final double z, final double w) {
        return this.add(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l add(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    @Override
    public MutableVec4l sub(final Vec4l v) {
        return this.sub(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l sub(final double x, final double y, final double z, final double w) {
        return this.sub(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l sub(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    @Override
    public MutableVec4l mul(final double a) {
        return this.mul(GenericMath.floorl(a));
    }

    @Override
    public MutableVec4l mul(final long a) {
        return this.mul(a, a, a, a);
    }

    @Override
    public MutableVec4l mul(final Vec4l v) {
        return this.mul(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l mul(final double x, final double y, final double z, final double w) {
        return this.mul(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l mul(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(this.x * x, this.y * y, this.z * z, this.w * w);
    }

    @Override
    public MutableVec4l div(final double a) {
        return this.div(GenericMath.floorl(a));
    }

    @Override
    public MutableVec4l div(final long a) {
        return this.div(a, a, a, a);
    }

    @Override
    public MutableVec4l div(final Vec4l v) {
        return this.div(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l div(final double x, final double y, final double z, final double w) {
        return this.div(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l div(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(this.x / x, this.y / y, this.z / z, this.w / w);
    }

    @Override
    public long dot(final Vec4l v) {
        return this.dot(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public long dot(final double x, final double y, final double z, final double w) {
        return this.dot(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public long dot(final long x, final long y, final long z, final long w) {
        return this.x * x + this.y * y + this.z * z + this.w * w;
    }

    @Override
    public MutableVec4l project(final Vec4l v) {
        return this.project(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l project(final double x, final double y, final double z, final double w) {
        return this.project(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l project(final long x, final long y, final long z, final long w) {
        final long lengthSquared = x * x + y * y + z * z + w * w;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = (double) this.dot(x, y, z, w) / lengthSquared;
        return new ImplMutableVec4l(a * x, a * y, a * z, a * w);
    }

    @Override
    public MutableVec4l pow(final double pow) {
        return this.pow(GenericMath.floorl(pow));
    }

    @Override
    public MutableVec4l pow(final long power) {
        return new ImplMutableVec4l(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
    }

    @Override
    public MutableVec4l abs() {
        return new ImplMutableVec4l(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
    }

    @Override
    public MutableVec4l negate() {
        return new ImplMutableVec4l(-this.x, -this.y, -this.z, -this.w);
    }

    @Override
    public MutableVec4l min(final Vec4l v) {
        return this.min(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l min(final double x, final double y, final double z, final double w) {
        return this.min(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l min(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
    }

    @Override
    public MutableVec4l max(final Vec4l v) {
        return this.max(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4l max(final double x, final double y, final double z, final double w) {
        return this.max(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l max(final long x, final long y, final long z, final long w) {
        return new ImplMutableVec4l(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
    }

    @Override
    public long distanceSquared(final Vec4l v) {
        return this.distanceSquared(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public long distanceSquared(final double x, final double y, final double z, final double w) {
        return this.distanceSquared(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public long distanceSquared(final long x, final long y, final long z, final long w) {
        final long dx = this.x - x;
        final long dy = this.y - y;
        final long dz = this.z - z;
        final long dw = this.w - w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    @Override
    public double distance(final Vec4l v) {
        return this.distance(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public double distance(final double x, final double y, final double z, final double w) {
        return this.distance(GenericMath.floorl(x), GenericMath.floorl(y), GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public double distance(final long x, final long y, final long z, final long w) {
        return (double) Math.sqrt(this.distanceSquared(x, y, z, w));
    }

    @Override
    public long lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    @Override
    public double length() {
        return (double) Math.sqrt(this.lengthSquared());
    }

    /**
     * Return the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        long value = this.x;
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
        long value = this.x;
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
    public MutableVec2l toVec2() {
        return new ImplMutableVec2l(this);
    }

    @Override
    public MutableVec3l toVec3() {
        return new ImplMutableVec3l(this);
    }

    @Override
    public VecNl toVecN() {
        return new VecNl(this);
    }

    @Override
    public long[] toArray() {
        return new long[]{this.x, this.y, this.z, this.w};
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
    public int compareTo(final Vec4l v) {
        return Long.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec4l)) {
            return false;
        }
        final Vec4l that = (Vec4l) other;
        if (Long.compare(that.w(), this.w) != 0) {
            return false;
        }
        if (Long.compare(that.x(), this.x) != 0) {
            return false;
        }
        if (Long.compare(that.y(), this.y) != 0) {
            return false;
        }
        if (Long.compare(that.z(), this.z) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (this.x != 0 ? Long.hashCode(this.x) : 0);
        result = 31 * result + (this.y != 0 ? Long.hashCode(this.y) : 0);
        result = 31 * result + (this.z != 0 ? Long.hashCode(this.z) : 0);
        return 31 * result + (this.w != 0 ? Long.hashCode(this.w) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
}
