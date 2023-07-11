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

public interface Vec2l extends Vec, Comparable<Vec2l> {
    Vec2l ZERO = new ImplVec2l(0, 0);
    Vec2l UNIT_X = new ImplVec2l(1, 0);
    Vec2l UNIT_Y = new ImplVec2l(0, 1);
    Vec2l ONE = new ImplVec2l(1, 1);

    static Vec2l of(final long n) {
        return n == 0 ? Vec2l.ZERO : new ImplVec2l(n, n);
    }

    static Vec2l of(final long x, final long y) {
        return x == 0 && y == 0 ? Vec2l.ZERO : new ImplVec2l(x, y);
    }

    long x();

    long y();

    Vec2l add(final Vec2l v);

    Vec2l add(final double x, final double y);

    Vec2l add(final long x, final long y);

    Vec2l sub(final Vec2l v);

    Vec2l sub(final double x, final double y);

    Vec2l sub(final long x, final long y);

    Vec2l mul(final double a);

    Vec2l mul(final long a);

    Vec2l mul(final Vec2l v);

    Vec2l mul(final double x, final double y);

    Vec2l mul(final long x, final long y);

    Vec2l div(final double a);

    Vec2l div(final long a);

    Vec2l div(final Vec2l v);

    Vec2l div(final double x, final double y);

    Vec2l div(final long x, final long y);

    long dot(final Vec2l v);

    long dot(final double x, final double y);

    long dot(final long x, final long y);

    Vec2l project(final Vec2l v);

    Vec2l project(final double x, final double y);

    Vec2l project(final long x, final long y);

    Vec2l pow(final double pow);

    Vec2l pow(final long power);

    Vec2l abs();

    Vec2l negate();

    Vec2l min(final Vec2l v);

    Vec2l min(final double x, final double y);

    Vec2l min(final long x, final long y);

    Vec2l max(final Vec2l v);

    Vec2l max(final double x, final double y);

    Vec2l max(final long x, final long y);

    long distanceSquared(final Vec2l v);

    long distanceSquared(final double x, final double y);

    long distanceSquared(final long x, final long y);

    double distance(final Vec2l v);

    double distance(final double x, final double y);

    double distance(final long x, final long y);

    long lengthSquared();

    double length();

    Vec3l toVec3();

    Vec3l toVec3(final double z);

    Vec3l toVec3(final long z);

    Vec4l toVec4();

    Vec4l toVec4(final double z, final double w);

    Vec4l toVec4(final long z, final long w);

    VecNl toVecN();

    long[] toArray();

    Vec2i toInt();

    Vec2l toLong();

    Vec2f toFloat();

    Vec2d toDouble();

    default MutableVec2l mut() {
        return new ImplMutableVec2l(x(), y());
    }

    default int axisCount() {
        return 2;
    }
}
