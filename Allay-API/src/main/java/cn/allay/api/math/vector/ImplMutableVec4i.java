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

final class ImplMutableVec4i implements MutableVec4i {
    private int x;
    private int y;
    private int z;
    private int w;

    public ImplMutableVec4i(final Vec2i v) {
        this(v, 0, 0);
    }

    public ImplMutableVec4i(final Vec2i v, final double z, final double w) {
        this(v, GenericMath.floor(z), GenericMath.floor(w));
    }

    public ImplMutableVec4i(final Vec2i v, final int z, final int w) {
        this(v.x(), v.y(), z, w);
    }

    public ImplMutableVec4i(final Vec3i v) {
        this(v, 0);
    }

    public ImplMutableVec4i(final Vec3i v, final double w) {
        this(v, GenericMath.floor(w));
    }

    public ImplMutableVec4i(final Vec3i v, final int w) {
        this(v.x(), v.y(), v.z(), w);
    }

    public ImplMutableVec4i(final VecNi v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, v.axisCount() > 3 ? v.get(3) : 0);
    }

    public ImplMutableVec4i(final double x, final double y, final double z, final double w) {
        this(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    public ImplMutableVec4i(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public void setX(final int x) {
        this.x = x;
    }

    @Override
    public int y() {
        return this.y;
    }

    @Override
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public int z() {
        return this.z;
    }

    @Override
    public void setZ(final int z) {
        this.z = z;
    }

    @Override
    public int w() {
        return this.w;
    }

    @Override
    public void setW(final int w) {
        this.w = w;
    }

    @Override
    public void setVec4(final Vec4i vec4) {
        this.x = vec4.x();
        this.y = vec4.y();
        this.z = vec4.z();
        this.w = vec4.w();
    }

    @Override
    public MutableVec4i add(final Vec4i v) {
        return this.add(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i add(final double x, final double y, final double z, final double w) {
        return this.add(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i add(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    @Override
    public MutableVec4i sub(final Vec4i v) {
        return this.sub(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i sub(final double x, final double y, final double z, final double w) {
        return this.sub(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i sub(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    @Override
    public MutableVec4i mul(final double a) {
        return this.mul(GenericMath.floor(a));
    }

    @Override
    public MutableVec4i mul(final int a) {
        return this.mul(a, a, a, a);
    }

    @Override
    public MutableVec4i mul(final Vec4i v) {
        return this.mul(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i mul(final double x, final double y, final double z, final double w) {
        return this.mul(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i mul(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(this.x * x, this.y * y, this.z * z, this.w * w);
    }

    @Override
    public MutableVec4i div(final double a) {
        return this.div(GenericMath.floor(a));
    }

    @Override
    public MutableVec4i div(final int a) {
        return this.div(a, a, a, a);
    }

    @Override
    public MutableVec4i div(final Vec4i v) {
        return this.div(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i div(final double x, final double y, final double z, final double w) {
        return this.div(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i div(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(this.x / x, this.y / y, this.z / z, this.w / w);
    }

    @Override
    public int dot(final Vec4i v) {
        return this.dot(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public int dot(final double x, final double y, final double z, final double w) {
        return this.dot(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public int dot(final int x, final int y, final int z, final int w) {
        return this.x * x + this.y * y + this.z * z + this.w * w;
    }

    @Override
    public MutableVec4i project(final Vec4i v) {
        return this.project(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i project(final double x, final double y, final double z, final double w) {
        return this.project(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i project(final int x, final int y, final int z, final int w) {
        final int lengthSquared = x * x + y * y + z * z + w * w;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = (float) this.dot(x, y, z, w) / lengthSquared;
        return new ImplMutableVec4i(a * x, a * y, a * z, a * w);
    }

    @Override
    public MutableVec4i pow(final double pow) {
        return this.pow(GenericMath.floor(pow));
    }

    @Override
    public MutableVec4i pow(final int power) {
        return new ImplMutableVec4i(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
    }

    @Override
    public MutableVec4i abs() {
        return new ImplMutableVec4i(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
    }

    @Override
    public MutableVec4i negate() {
        return new ImplMutableVec4i(-this.x, -this.y, -this.z, -this.w);
    }

    @Override
    public MutableVec4i min(final Vec4i v) {
        return this.min(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i min(final double x, final double y, final double z, final double w) {
        return this.min(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i min(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
    }

    @Override
    public MutableVec4i max(final Vec4i v) {
        return this.max(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4i max(final double x, final double y, final double z, final double w) {
        return this.max(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public MutableVec4i max(final int x, final int y, final int z, final int w) {
        return new ImplMutableVec4i(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
    }

    @Override
    public int distanceSquared(final Vec4i v) {
        return this.distanceSquared(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public int distanceSquared(final double x, final double y, final double z, final double w) {
        return this.distanceSquared(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public int distanceSquared(final int x, final int y, final int z, final int w) {
        final int dx = this.x - x;
        final int dy = this.y - y;
        final int dz = this.z - z;
        final int dw = this.w - w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    @Override
    public float distance(final Vec4i v) {
        return this.distance(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public float distance(final double x, final double y, final double z, final double w) {
        return this.distance(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public float distance(final int x, final int y, final int z, final int w) {
        return (float) Math.sqrt(this.distanceSquared(x, y, z, w));
    }

    @Override
    public int lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    /**
     * Return the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        int value = this.x;
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
        int value = this.x;
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
    public MutableVec2i toVec2() {
        return new ImplMutableVec2i(this);
    }

    @Override
    public MutableVec3i toVec3() {
        return new ImplMutableVec3i(this);
    }

    @Override
    public VecNi toVecN() {
        return new VecNi(this);
    }

    @Override
    public int[] toArray() {
        return new int[]{this.x, this.y, this.z, this.w};
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
    public int compareTo(final Vec4i v) {
        return Integer.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec4i that)) {
            return false;
        }
        if (that.w() != this.w) {
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

    @Override
    public int hashCode() {
        int result = (this.x != 0 ? Integer.hashCode(this.x) : 0);
        result = 31 * result + (this.y != 0 ? Integer.hashCode(this.y) : 0);
        result = 31 * result + (this.z != 0 ? Integer.hashCode(this.z) : 0);
        return 31 * result + (this.w != 0 ? Integer.hashCode(this.w) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
}
