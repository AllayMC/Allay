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
record ImplVec2i(int x, int y) implements Vec2i {
    public ImplVec2i(final Vec3i v) {
        this(v.x(), v.y());
    }

    public ImplVec2i(final Vec4i v) {
        this(v.x(), v.y());
    }

    public ImplVec2i(final VecNi v) {
        this(v.get(0), v.get(1));
    }

    public ImplVec2i(final double x, final double y) {
        this(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i add(final Vec2i v) {
        return this.add(v.x(), v.y());
    }

    @Override
    public Vec2i add(final double x, final double y) {
        return this.add(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i add(final int x, final int y) {
        return new ImplVec2i(this.x + x, this.y + y);
    }

    @Override
    public Vec2i sub(final Vec2i v) {
        return this.sub(v.x(), v.y());
    }

    @Override
    public Vec2i sub(final double x, final double y) {
        return this.sub(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i sub(final int x, final int y) {
        return new ImplVec2i(this.x - x, this.y - y);
    }

    public Vec2i mul(final double a) {
        return this.mul(GenericMath.floor(a));
    }

    @Override
    public Vec2i mul(final int a) {
        return this.mul(a, a);
    }

    @Override
    public Vec2i mul(final Vec2i v) {
        return this.mul(v.x(), v.y());
    }

    @Override
    public Vec2i mul(final double x, final double y) {
        return this.mul(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i mul(final int x, final int y) {
        return new ImplVec2i(this.x * x, this.y * y);
    }

    @Override
    public Vec2i div(final double a) {
        return this.div(GenericMath.floor(a));
    }

    @Override
    public Vec2i div(final int a) {
        return this.div(a, a);
    }

    @Override
    public Vec2i div(final Vec2i v) {
        return this.div(v.x(), v.y());
    }

    @Override
    public Vec2i div(final double x, final double y) {
        return this.div(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i div(final int x, final int y) {
        return new ImplVec2i(this.x / x, this.y / y);
    }

    @Override
    public int dot(final Vec2i v) {
        return this.dot(v.x(), v.y());
    }

    @Override
    public int dot(final double x, final double y) {
        return this.dot(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public int dot(final int x, final int y) {
        return this.x * x + this.y * y;
    }

    @Override
    public Vec2i project(final Vec2i v) {
        return this.project(v.x(), v.y());
    }

    @Override
    public Vec2i project(final double x, final double y) {
        return this.project(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i project(final int x, final int y) {
        final int lengthSquared = x * x + y * y;
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vector");
        }
        final float a = (float) this.dot(x, y) / lengthSquared;
        return new ImplVec2i(a * x, a * y);
    }

    public Vec2i pow(final double pow) {
        return this.pow(GenericMath.floor(pow));
    }

    @Override
    public Vec2i pow(final int power) {
        return new ImplVec2i(Math.pow(this.x, power), Math.pow(this.y, power));
    }

    @Override
    public Vec2i abs() {
        return new ImplVec2i(Math.abs(this.x), Math.abs(this.y));
    }

    @Override
    public Vec2i negate() {
        return new ImplVec2i(-this.x, -this.y);
    }

    @Override
    public Vec2i min(final Vec2i v) {
        return this.min(v.x(), v.y());
    }

    @Override
    public Vec2i min(final double x, final double y) {
        return this.min(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i min(final int x, final int y) {
        return new ImplVec2i(Math.min(this.x, x), Math.min(this.y, y));
    }

    @Override
    public Vec2i max(final Vec2i v) {
        return this.max(v.x(), v.y());
    }

    @Override
    public Vec2i max(final double x, final double y) {
        return this.max(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public Vec2i max(final int x, final int y) {
        return new ImplVec2i(Math.max(this.x, x), Math.max(this.y, y));
    }

    @Override
    public int distanceSquared(final Vec2i v) {
        return this.distanceSquared(v.x(), v.y());
    }

    @Override
    public int distanceSquared(final double x, final double y) {
        return this.distanceSquared(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public int distanceSquared(final int x, final int y) {
        final int dx = this.x - x;
        final int dy = this.y - y;
        return dx * dx + dy * dy;
    }

    @Override
    public float distance(final Vec2i v) {
        return this.distance(v.x(), v.y());
    }

    @Override
    public float distance(final double x, final double y) {
        return this.distance(GenericMath.floor(x), GenericMath.floor(y));
    }

    @Override
    public float distance(final int x, final int y) {
        return (float) Math.sqrt(this.distanceSquared(x, y));
    }

    @Override
    public int lengthSquared() {
        return this.x * this.x + this.y * this.y;
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
    public Vec3i toVec3() {
        return this.toVec3(0);
    }

    @Override
    public Vec3i toVec3(final double z) {
        return this.toVec3(GenericMath.floor(z));
    }

    @Override
    public Vec3i toVec3(final int z) {
        return new ImplVec3i(this, z);
    }

    @Override
    public Vec4i toVec4() {
        return this.toVec4(0, 0);
    }

    @Override
    public Vec4i toVec4(final double z, final double w) {
        return this.toVec4(GenericMath.floor(z), GenericMath.floor(w));
    }

    @Override
    public Vec4i toVec4(final int z, final int w) {
        return new ImplVec4i(this, z, w);
    }

    @Override
    public VecNi toVecN() {
        return new VecNi(this);
    }

    @Override
    public int[] toArray() {
        return new int[]{this.x, this.y};
    }

    @Override
    public Vec2i toInt() {
        return new ImplVec2i(this.x, this.y);
    }

    @Override
    public Vec2l toLong() {
        return new ImplVec2l(this.x, this.y);
    }

    @Override
    public Vec2f toFloat() {
        return new ImplVec2f(this.x, this.y);
    }

    @Override
    public Vec2d toDouble() {
        return new ImplVec2d((double) this.x, (double) this.y);
    }

    @Override
    public int compareTo(final Vec2i v) {
        return Integer.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vec2i)) {
            return false;
        }
        final Vec2i that = (Vec2i) other;
        if (that.x() != this.x) {
            return false;
        }
        if (that.y() != this.y) {
            return false;
        }
        return true;
    }
}
