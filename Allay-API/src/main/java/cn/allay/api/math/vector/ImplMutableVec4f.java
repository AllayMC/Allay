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

final class ImplMutableVec4f implements MutableVec4f {
    private float x;
    private float y;
    private float z;
    private float w;

    public ImplMutableVec4f(final Vec2f v) {
        this(v, 0, 0);
    }

    public ImplMutableVec4f(final Vec2f v, final double z, final double w) {
        this(v, (float) z, (float) w);
    }

    public ImplMutableVec4f(final Vec2f v, final float z, final float w) {
        this(v.x(), v.y(), z, w);
    }

    public ImplMutableVec4f(final Vec3f v) {
        this(v, 0);
    }

    public ImplMutableVec4f(final Vec3f v, final double w) {
        this(v, (float) w);
    }

    public ImplMutableVec4f(final Vec3f v, final float w) {
        this(v.x(), v.y(), v.z(), w);
    }

    public ImplMutableVec4f(final VecNf v) {
        this(v.get(0), v.get(1), v.axisCount() > 2 ? v.get(2) : 0, v.axisCount() > 3 ? v.get(3) : 0);
    }

    public ImplMutableVec4f(final double x, final double y, final double z, final double w) {
        this((float) x, (float) y, (float) z, (float) w);
    }

    public ImplMutableVec4f(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public float x() {
        return this.x;
    }

    @Override
    public void setX(final float x) {
        this.x = x;
    }

    @Override
    public void setX(final double x) {
        setX((float) x);
    }

    @Override
    public float y() {
        return this.y;
    }

    @Override
    public void setY(final float y) {
        this.y = y;
    }

    @Override
    public void setY(final double y) {
        setY((float) y);
    }

    @Override
    public float z() {
        return this.z;
    }

    @Override
    public void setZ(final float z) {
        this.z = z;
    }

    @Override
    public void setZ(final double z) {
        setZ((float) z);
    }

    @Override
    public float w() {
        return this.w;
    }

    @Override
    public void setW(final float w) {
        this.w = w;
    }

    @Override
    public void setW(final double w) {
        setW((float) w);
    }

    @Override
    public void setVec4(final Vec4f vec4) {
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
    public MutableVec4f add(final Vec4f v) {
        return this.add(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f add(final double x, final double y, final double z, final double w) {
        return this.add((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f add(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    @Override
    public MutableVec4f sub(final Vec4f v) {
        return this.sub(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f sub(final double x, final double y, final double z, final double w) {
        return this.sub((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f sub(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    @Override
    public MutableVec4f mul(final double a) {
        return this.mul((float) a);
    }

    @Override
    public MutableVec4f mul(final float a) {
        return this.mul(a, a, a, a);
    }

    @Override
    public MutableVec4f mul(final Vec4f v) {
        return this.mul(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f mul(final double x, final double y, final double z, final double w) {
        return this.mul((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f mul(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(this.x * x, this.y * y, this.z * z, this.w * w);
    }

    @Override
    public MutableVec4f div(final double a) {
        return this.div((float) a);
    }

    @Override
    public MutableVec4f div(final float a) {
        return this.div(a, a, a, a);
    }

    @Override
    public MutableVec4f div(final Vec4f v) {
        return this.div(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f div(final double x, final double y, final double z, final double w) {
        return this.div((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f div(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(this.x / x, this.y / y, this.z / z, this.w / w);
    }

    @Override
    public float dot(final Vec4f v) {
        return this.dot(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public float dot(final double x, final double y, final double z, final double w) {
        return this.dot((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public float dot(final float x, final float y, final float z, final float w) {
        return this.x * x + this.y * y + this.z * z + this.w * w;
    }

    @Override
    public MutableVec4f project(final Vec4f v) {
        return this.project(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f project(final double x, final double y, final double z, final double w) {
        return this.project((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f project(final float x, final float y, final float z, final float w) {
        final float lengthSquared = x * x + y * y + z * z + w * w;
        if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = this.dot(x, y, z, w) / lengthSquared;
        return new ImplMutableVec4f(a * x, a * y, a * z, a * w);
    }

    @Override
    public MutableVec4f pow(final double pow) {
        return this.pow((float) pow);
    }

    @Override
    public MutableVec4f pow(final float power) {
        return new ImplMutableVec4f(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
    }

    @Override
    public MutableVec4f ceil() {
        return new ImplMutableVec4f(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), Math.ceil(this.w));
    }

    @Override
    public MutableVec4f floor() {
        return new ImplMutableVec4f(GenericMath.floor(this.x), GenericMath.floor(this.y), GenericMath.floor(this.z), GenericMath.floor(this.w));
    }

    @Override
    public MutableVec4f round() {
        return new ImplMutableVec4f(Math.round(this.x), Math.round(this.y), Math.round(this.z), Math.round(this.w));
    }

    @Override
    public MutableVec4f abs() {
        return new ImplMutableVec4f(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
    }

    @Override
    public MutableVec4f negate() {
        return new ImplMutableVec4f(-this.x, -this.y, -this.z, -this.w);
    }

    @Override
    public MutableVec4f min(final Vec4f v) {
        return this.min(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f min(final double x, final double y, final double z, final double w) {
        return this.min((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f min(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
    }

    @Override
    public MutableVec4f max(final Vec4f v) {
        return this.max(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public MutableVec4f max(final double x, final double y, final double z, final double w) {
        return this.max((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public MutableVec4f max(final float x, final float y, final float z, final float w) {
        return new ImplMutableVec4f(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
    }

    @Override
    public float distanceSquared(final Vec4f v) {
        return this.distanceSquared(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public float distanceSquared(final double x, final double y, final double z, final double w) {
        return this.distanceSquared((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public float distanceSquared(final float x, final float y, final float z, final float w) {
        final float dx = this.x - x;
        final float dy = this.y - y;
        final float dz = this.z - z;
        final float dw = this.w - w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    @Override
    public float distance(final Vec4f v) {
        return this.distance(v.x(), v.y(), v.z(), v.w());
    }

    @Override
    public float distance(final double x, final double y, final double z, final double w) {
        return this.distance((float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public float distance(final float x, final float y, final float z, final float w) {
        return (float) Math.sqrt(this.distanceSquared(x, y, z, w));
    }

    @Override
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    @Override
    public MutableVec4f normalize() {
        final float length = this.length();
        if (Math.abs(length) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplMutableVec4f(this.x / length, this.y / length, this.z / length, this.w / length);
    }

    /**
     * Return the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        float value = this.x;
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
        float value = this.x;
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
    public MutableVec2f toVec2() {
        return new ImplMutableVec2f(this);
    }

    @Override
    public MutableVec3f toVec3() {
        return new ImplMutableVec3f(this);
    }

    @Override
    public VecNf toVecN() {
        return new VecNf(this);
    }

    @Override
    public float[] toArray() {
        return new float[]{this.x, this.y, this.z, this.w};
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
    public int compareTo(final Vec4f v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec4f)) {
            return false;
        }
        final Vec4f that = (Vec4f) other;
        if (Float.compare(that.w(), this.w) != 0) {
            return false;
        }
        if (Float.compare(that.x(), this.x) != 0) {
            return false;
        }
        if (Float.compare(that.y(), this.y) != 0) {
            return false;
        }
        if (Float.compare(that.z(), this.z) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (this.x != +0.0f ? Float.hashCode(this.x) : 0);
        result = 31 * result + (this.y != +0.0f ? Float.hashCode(this.y) : 0);
        result = 31 * result + (this.z != +0.0f ? Float.hashCode(this.z) : 0);
        return 31 * result + (this.w != +0.0f ? Float.hashCode(this.w) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
}
