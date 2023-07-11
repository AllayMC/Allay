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

public interface Vec4l extends Vec, Comparable<Vec4l> {
    Vec4l ZERO = new ImplVec4l(0, 0, 0, 0);
    Vec4l UNIT_X = new ImplVec4l(1, 0, 0, 0);
    Vec4l UNIT_Y = new ImplVec4l(0, 1, 0, 0);
    Vec4l UNIT_Z = new ImplVec4l(0, 0, 1, 0);
    Vec4l UNIT_W = new ImplVec4l(0, 0, 0, 1);
    Vec4l ONE = new ImplVec4l(1, 1, 1, 1);

    static Vec4l of(final long n) {
        return n == 0 ? Vec4l.ZERO : new ImplVec4l(n, n, n, n);
    }

    static Vec4l of(final long x, final long y, final long z, final long w) {
        return x == 0 && y == 0 && z == 0 && w == 0 ? Vec4l.ZERO : new ImplVec4l(x, y, z, w);
    }

    long x();

    long y();

    long z();

    long w();

    Vec4l add(final Vec4l v);

    Vec4l add(final double x, final double y, final double z, final double w);

    Vec4l add(final long x, final long y, final long z, final long w);

    Vec4l sub(final Vec4l v);

    Vec4l sub(final double x, final double y, final double z, final double w);

    Vec4l sub(final long x, final long y, final long z, final long w);

    Vec4l mul(final double a);

    Vec4l mul(final long a);

    Vec4l mul(final Vec4l v);

    Vec4l mul(final double x, final double y, final double z, final double w);

    Vec4l mul(final long x, final long y, final long z, final long w);

    Vec4l div(final double a);

    Vec4l div(final long a);

    Vec4l div(final Vec4l v);

    Vec4l div(final double x, final double y, final double z, final double w);

    Vec4l div(final long x, final long y, final long z, final long w);

    long dot(final Vec4l v);

    long dot(final double x, final double y, final double z, final double w);

    long dot(final long x, final long y, final long z, final long w);

    Vec4l project(final Vec4l v);

    Vec4l project(final double x, final double y, final double z, final double w);

    Vec4l project(final long x, final long y, final long z, final long w);

    Vec4l pow(final double pow);

    Vec4l pow(final long power);

    Vec4l abs();

    Vec4l negate();

    Vec4l min(final Vec4l v);

    Vec4l min(final double x, final double y, final double z, final double w);

    Vec4l min(final long x, final long y, final long z, final long w);

    Vec4l max(final Vec4l v);

    Vec4l max(final double x, final double y, final double z, final double w);

    Vec4l max(final long x, final long y, final long z, final long w);

    long distanceSquared(final Vec4l v);

    long distanceSquared(final double x, final double y, final double z, final double w);

    long distanceSquared(final long x, final long y, final long z, final long w);

    double distance(final Vec4l v);

    double distance(final double x, final double y, final double z, final double w);

    double distance(final long x, final long y, final long z, final long w);

    long lengthSquared();

    double length();

    Vec2l toVec2();

    Vec3l toVec3();

    VecNl toVecN();

    long[] toArray();

    Vec4i toInt();

    Vec4l toLong();

    Vec4f toFloat();

    Vec4d toDouble();

    default MutableVec4l mut() {
        return new ImplMutableVec4l(x(), y(), z(), w());
    }

    default int axisCount() {
        return 4;
    }
}
