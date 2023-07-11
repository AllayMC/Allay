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

final class ImplMutableVec2l implements MutableVec2l {
    private long x;
    private long y;

    public ImplMutableVec2l(final Vec3l v) {
        this(v.x(), v.y());
    }

    public ImplMutableVec2l(final Vec4l v) {
        this(v.x(), v.y());
    }

    public ImplMutableVec2l(final VecNl v) {
        this(v.get(0), v.get(1));
    }

    public ImplMutableVec2l(final double x, final double y) {
        this(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    public ImplMutableVec2l(final long x, final long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public long x() {
        return this.x;
    }

    @Override
    public long y() {
        return this.y;
    }

    @Override
    public void setX(final long x) {
        this.x = x;
    }

    @Override
    public void setY(final long y) {
        this.y = y;
    }

    @Override
    public void setVec2(final Vec2l vec2) {
        this.x = vec2.x();
        this.y = vec2.y();
    }

    @Override
    public MutableVec2l add(final Vec2l v) {
        return this.add(v.x(), v.y());
    }

    @Override
    public MutableVec2l add(final double x, final double y) {
        return this.add(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l add(final long x, final long y) {
        return new ImplMutableVec2l(this.x + x, this.y + y);
    }

    @Override
    public MutableVec2l sub(final Vec2l v) {
        return this.sub(v.x(), v.y());
    }

    @Override
    public MutableVec2l sub(final double x, final double y) {
        return this.sub(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l sub(final long x, final long y) {
        return new ImplMutableVec2l(this.x - x, this.y - y);
    }

    public MutableVec2l mul(final double a) {
        return this.mul(GenericMath.floorl(a));
    }

    @Override
    public MutableVec2l mul(final long a) {
        return this.mul(a, a);
    }

    @Override
    public MutableVec2l mul(final Vec2l v) {
        return this.mul(v.x(), v.y());
    }

    @Override
    public MutableVec2l mul(final double x, final double y) {
        return this.mul(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l mul(final long x, final long y) {
        return new ImplMutableVec2l(this.x * x, this.y * y);
    }

    @Override
    public MutableVec2l div(final double a) {
        return this.div(GenericMath.floorl(a));
    }

    @Override
    public MutableVec2l div(final long a) {
        return this.div(a, a);
    }

    @Override
    public MutableVec2l div(final Vec2l v) {
        return this.div(v.x(), v.y());
    }

    @Override
    public MutableVec2l div(final double x, final double y) {
        return this.div(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l div(final long x, final long y) {
        return new ImplMutableVec2l(this.x / x, this.y / y);
    }

    @Override
    public long dot(final Vec2l v) {
        return this.dot(v.x(), v.y());
    }

    @Override
    public long dot(final double x, final double y) {
        return this.dot(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public long dot(final long x, final long y) {
        return this.x * x + this.y * y;
    }

    @Override
    public MutableVec2l project(final Vec2l v) {
        return this.project(v.x(), v.y());
    }

    @Override
    public MutableVec2l project(final double x, final double y) {
        return this.project(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l project(final long x, final long y) {
        final long lengthSquared = x * x + y * y;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vector");
        }
        final double a = (double) this.dot(x, y) / lengthSquared;
        return new ImplMutableVec2l(a * x, a * y);
    }

    public MutableVec2l pow(final double pow) {
        return this.pow(GenericMath.floorl(pow));
    }

    @Override
    public MutableVec2l pow(final long power) {
        return new ImplMutableVec2l(Math.pow(this.x, power), Math.pow(this.y, power));
    }

    @Override
    public MutableVec2l abs() {
        return new ImplMutableVec2l(Math.abs(this.x), Math.abs(this.y));
    }

    @Override
    public MutableVec2l negate() {
        return new ImplMutableVec2l(-this.x, -this.y);
    }

    @Override
    public MutableVec2l min(final Vec2l v) {
        return this.min(v.x(), v.y());
    }

    @Override
    public MutableVec2l min(final double x, final double y) {
        return this.min(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l min(final long x, final long y) {
        return new ImplMutableVec2l(Math.min(this.x, x), Math.min(this.y, y));
    }

    @Override
    public MutableVec2l max(final Vec2l v) {
        return this.max(v.x(), v.y());
    }

    @Override
    public MutableVec2l max(final double x, final double y) {
        return this.max(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public MutableVec2l max(final long x, final long y) {
        return new ImplMutableVec2l(Math.max(this.x, x), Math.max(this.y, y));
    }

    @Override
    public long distanceSquared(final Vec2l v) {
        return this.distanceSquared(v.x(), v.y());
    }

    @Override
    public long distanceSquared(final double x, final double y) {
        return this.distanceSquared(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public long distanceSquared(final long x, final long y) {
        final long dx = this.x - x;
        final long dy = this.y - y;
        return dx * dx + dy * dy;
    }

    @Override
    public double distance(final Vec2l v) {
        return this.distance(v.x(), v.y());
    }

    @Override
    public double distance(final double x, final double y) {
        return this.distance(GenericMath.floorl(x), GenericMath.floorl(y));
    }

    @Override
    public double distance(final long x, final long y) {
        return (double) Math.sqrt(this.distanceSquared(x, y));
    }

    @Override
    public long lengthSquared() {
        return this.x * this.x + this.y * this.y;
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
    public MutableVec3l toVec3() {
        return this.toVec3(0);
    }

    @Override
    public MutableVec3l toVec3(final double z) {
        return this.toVec3(GenericMath.floorl(z));
    }

    @Override
    public MutableVec3l toVec3(final long z) {
        return new ImplMutableVec3l(this, z);
    }

    @Override
    public MutableVec4l toVec4() {
        return this.toVec4(0, 0);
    }

    @Override
    public MutableVec4l toVec4(final double z, final double w) {
        return this.toVec4(GenericMath.floorl(z), GenericMath.floorl(w));
    }

    @Override
    public MutableVec4l toVec4(final long z, final long w) {
        return new ImplMutableVec4l(this, z, w);
    }

    @Override
    public VecNl toVecN() {
        return new VecNl(this);
    }

    @Override
    public long[] toArray() {
        return new long[]{this.x, this.y};
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
    public int compareTo(final Vec2l v) {
        return Long.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec2l)) {
            return false;
        }
        final Vec2l that = (Vec2l) other;
        if (that.x() != this.x) {
            return false;
        }
        if (that.y() != this.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int result = (this.x != 0 ? Long.hashCode(this.x) : 0);
        return 31 * result + (this.y != 0 ? Long.hashCode(this.y) : 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
