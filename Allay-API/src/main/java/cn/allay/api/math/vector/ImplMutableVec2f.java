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

final class ImplMutableVec2f implements MutableVec2f {
    private float x;
    private float y;

    public ImplMutableVec2f(final Vec3f v) {
        this(v.x(), v.y());
    }

    public ImplMutableVec2f(final Vec4f v) {
        this(v.x(), v.y());
    }

    public ImplMutableVec2f(final VecNf v) {
        this(v.get(0), v.get(1));
    }

    public ImplMutableVec2f(final double x, final double y) {
        this((float) x, (float) y);
    }

    public ImplMutableVec2f(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float x() {
        return this.x;
    }

    @Override
    public float y() {
        return this.y;
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
    public void setY(final float y) {
        this.y = y;
    }

    @Override
    public void setY(final double y) {
        setY((float) y);
    }

    @Override
    public void setVec2(final Vec2f vec2) {
        this.x = vec2.x();
        this.y = vec2.y();
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
    public MutableVec2f add(final Vec2f v) {
        return this.add(v.x(), v.y());
    }

    @Override
    public MutableVec2f add(final double x, final double y) {
        return this.add((float) x, (float) y);
    }

    @Override
    public MutableVec2f add(final float x, final float y) {
        return new ImplMutableVec2f(this.x + x, this.y + y);
    }

    @Override
    public MutableVec2f sub(final Vec2f v) {
        return this.sub(v.x(), v.y());
    }

    @Override
    public MutableVec2f sub(final double x, final double y) {
        return this.sub((float) x, (float) y);
    }

    @Override
    public MutableVec2f sub(final float x, final float y) {
        return new ImplMutableVec2f(this.x - x, this.y - y);
    }

    @Override
    public MutableVec2f mul(final double a) {
        return this.mul((float) a);
    }

    @Override
    public MutableVec2f mul(final float a) {
        return this.mul(a, a);
    }

    @Override
    public MutableVec2f mul(final Vec2f v) {
        return this.mul(v.x(), v.y());
    }

    @Override
    public MutableVec2f mul(final double x, final double y) {
        return this.mul((float) x, (float) y);
    }

    @Override
    public MutableVec2f mul(final float x, final float y) {
        return new ImplMutableVec2f(this.x * x, this.y * y);
    }

    @Override
    public MutableVec2f div(final double a) {
        return this.div((float) a);
    }

    @Override
    public MutableVec2f div(final float a) {
        return this.div(a, a);
    }

    @Override
    public MutableVec2f div(final Vec2f v) {
        return this.div(v.x(), v.y());
    }

    @Override
    public MutableVec2f div(final double x, final double y) {
        return this.div((float) x, (float) y);
    }

    @Override
    public MutableVec2f div(final float x, final float y) {
        return new ImplMutableVec2f(this.x / x, this.y / y);
    }

    @Override
    public float dot(final Vec2f v) {
        return this.dot(v.x(), v.y());
    }

    @Override
    public float dot(final double x, final double y) {
        return this.dot((float) x, (float) y);
    }

    @Override
    public float dot(final float x, final float y) {
        return this.x * x + this.y * y;
    }

    @Override
    public MutableVec2f project(final Vec2f v) {
        return this.project(v.x(), v.y());
    }

    @Override
    public MutableVec2f project(final double x, final double y) {
        return this.project((float) x, (float) y);
    }

    @Override
    public MutableVec2f project(final float x, final float y) {
        final float lengthSquared = x * x + y * y;
        if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = this.dot(x, y) / lengthSquared;
        return new ImplMutableVec2f(a * x, a * y);
    }

    @Override
    public MutableVec2f pow(final double pow) {
        return this.pow((float) pow);
    }

    @Override
    public MutableVec2f pow(final float power) {
        return new ImplMutableVec2f(Math.pow(this.x, power), Math.pow(this.y, power));
    }

    @Override
    public MutableVec2f ceil() {
        return new ImplMutableVec2f(Math.ceil(this.x), Math.ceil(this.y));
    }

    @Override
    public MutableVec2f floor() {
        return new ImplMutableVec2f(GenericMath.floor(this.x), GenericMath.floor(this.y));
    }

    @Override
    public MutableVec2f round() {
        return new ImplMutableVec2f(Math.round(this.x), Math.round(this.y));
    }

    @Override
    public MutableVec2f abs() {
        return new ImplMutableVec2f(Math.abs(this.x), Math.abs(this.y));
    }

    @Override
    public MutableVec2f negate() {
        return new ImplMutableVec2f(-this.x, -this.y);
    }

    @Override
    public MutableVec2f min(final Vec2f v) {
        return this.min(v.x(), v.y());
    }

    @Override
    public MutableVec2f min(final double x, final double y) {
        return this.min((float) x, (float) y);
    }

    @Override
    public MutableVec2f min(final float x, final float y) {
        return new ImplMutableVec2f(Math.min(this.x, x), Math.min(this.y, y));
    }

    @Override
    public MutableVec2f max(final Vec2f v) {
        return this.max(v.x(), v.y());
    }

    @Override
    public MutableVec2f max(final double x, final double y) {
        return this.max((float) x, (float) y);
    }

    @Override
    public MutableVec2f max(final float x, final float y) {
        return new ImplMutableVec2f(Math.max(this.x, x), Math.max(this.y, y));
    }

    @Override
    public float distanceSquared(final Vec2f v) {
        return this.distanceSquared(v.x(), v.y());
    }

    @Override
    public float distanceSquared(final double x, final double y) {
        return this.distanceSquared((float) x, (float) y);
    }

    @Override
    public float distanceSquared(final float x, final float y) {
        final float dx = this.x - x;
        final float dy = this.y - y;
        return dx * dx + dy * dy;
    }

    @Override
    public float distance(final Vec2f v) {
        return this.distance(v.x(), v.y());
    }

    @Override
    public float distance(final double x, final double y) {
        return this.distance((float) x, (float) y);
    }

    @Override
    public float distance(final float x, final float y) {
        return (float) Math.sqrt(this.distanceSquared(x, y));
    }

    @Override
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    @Override
    public MutableVec2f normalize() {
        final float length = this.length();
        if (Math.abs(length) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        return new ImplMutableVec2f(this.x / length, this.y / length);
    }

    /**
     * Return the axis with the minimal value.
     *
     * @return {@link int} axis with minimal value
     */
    @Override
    public int minAxis() {
        return this.x < this.y ? 0 : 1;
    }

    /**
     * Return the axis with the maximum value.
     *
     * @return {@link int} axis with maximum value
     */
    @Override
    public int maxAxis() {
        return this.x > this.y ? 0 : 1;
    }

    @Override
    public MutableVec3f toVec3() {
        return this.toVec3(0);
    }

    @Override
    public MutableVec3f toVec3(final double z) {
        return this.toVec3((float) z);
    }

    @Override
    public MutableVec3f toVec3(final float z) {
        return new ImplMutableVec3f(this, z);
    }

    @Override
    public MutableVec4f toVec4() {
        return this.toVec4(0, 0);
    }

    @Override
    public MutableVec4f toVec4(final double z, final double w) {
        return this.toVec4((float) z, (float) w);
    }

    @Override
    public MutableVec4f toVec4(final float z, final float w) {
        return new ImplMutableVec4f(this, z, w);
    }

    @Override
    public VecNf toVecN() {
        return new VecNf(this);
    }

    @Override
    public float[] toArray() {
        return new float[]{this.x, this.y};
    }

    @Override
    public MutableVec2i toInt() {
        return new ImplMutableVec2i(this.x, this.y);
    }

    @Override
    public MutableVec2l toLong() {
        return new ImplMutableVec2l(this.x, this.y);
    }

    @Override
    public MutableVec2f toFloat() {
        return new ImplMutableVec2f(this.x, this.y);
    }

    @Override
    public MutableVec2d toDouble() {
        return new ImplMutableVec2d(this.x, this.y);
    }

    @Override
    public int compareTo(final Vec2f v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec2f that)) {
            return false;
        }
        if (Float.compare(that.x(), this.x) != 0) {
            return false;
        }
        return Float.compare(that.y(), this.y) == 0;
    }

    @Override
    public int hashCode() {
        final int result = (this.x != +0.0f ? Float.hashCode(this.x) : 0);
        return 31 * result + (this.y != +0.0f ? Float.hashCode(this.y) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
