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

public interface Vec3l extends Vec, Comparable<Vec3l> {
    Vec3l ZERO = new ImplVec3l(0, 0, 0);
    Vec3l UNIT_X = new ImplVec3l(1, 0, 0);
    Vec3l UNIT_Y = new ImplVec3l(0, 1, 0);
    Vec3l UNIT_Z = new ImplVec3l(0, 0, 1);
    Vec3l ONE = new ImplVec3l(1, 1, 1);
    Vec3l RIGHT = ImplVec3l.UNIT_X;
    Vec3l UP = ImplVec3l.UNIT_Y;
    Vec3l FORWARD = ImplVec3l.UNIT_Z;

    static Vec3l of(final long n) {
        return n == 0 ? Vec3l.ZERO : new ImplVec3l(n, n, n);
    }

    static Vec3l of(final long x, final long y, final long z) {
        return x == 0 && y == 0 && z == 0 ? Vec3l.ZERO : new ImplVec3l(x, y, z);
    }

    long x();

    long y();

    long z();

    Vec3l add(final Vec3l v);

    Vec3l add(final double x, final double y, final double z);

    Vec3l add(final long x, final long y, final long z);

    Vec3l sub(final Vec3l v);

    Vec3l sub(final double x, final double y, final double z);

    Vec3l sub(final long x, final long y, final long z);

    Vec3l mul(final double a);

    Vec3l mul(final long a);

    Vec3l mul(final Vec3l v);

    Vec3l mul(final double x, final double y, final double z);

    Vec3l mul(final long x, final long y, final long z);

    Vec3l div(final double a);

    Vec3l div(final long a);

    Vec3l div(final Vec3l v);

    Vec3l div(final double x, final double y, final double z);

    Vec3l div(final long x, final long y, final long z);

    long dot(final Vec3l v);

    long dot(final double x, final double y, final double z);

    long dot(final long x, final long y, final long z);

    Vec3l project(final Vec3l v);

    Vec3l project(final double x, final double y, final double z);

    Vec3l project(final long x, final long y, final long z);

    Vec3l cross(final Vec3l v);

    Vec3l cross(final double x, final double y, final double z);

    Vec3l cross(final long x, final long y, final long z);

    Vec3l pow(final double pow);

    Vec3l pow(final long power);

    Vec3l abs();

    Vec3l negate();

    Vec3l min(final Vec3l v);

    Vec3l min(final double x, final double y, final double z);

    Vec3l min(final long x, final long y, final long z);

    Vec3l max(final Vec3l v);

    Vec3l max(final double x, final double y, final double z);

    Vec3l max(final long x, final long y, final long z);

    long distanceSquared(final Vec3l v);

    long distanceSquared(final double x, final double y, final double z);

    long distanceSquared(final long x, final long y, final long z);

    double distance(final Vec3l v);

    double distance(final double x, final double y, final double z);

    double distance(final long x, final long y, final long z);

    long lengthSquared();

    double length();

    Vec2l toVec2();

    Vec2l toVec2(final boolean useZ);

    Vec4l toVec4();

    Vec4l toVec4(final double w);

    Vec4l toVec4(final long w);

    VecNl toVecN();

    long[] toArray();

    Vec3i toInt();

    Vec3l toLong();

    Vec3f toFloat();

    Vec3d toDouble();

    default MutableVec3l mut() {
        return new ImplMutableVec3l(x(), y(), z());
    }

    default int axisCount() {
        return 3;
    }
}
