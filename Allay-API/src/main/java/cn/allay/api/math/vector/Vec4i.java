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

public interface Vec4i extends Vec, Comparable<Vec4i> {
    Vec4i ZERO = new ImplVec4i(0, 0, 0, 0);
    Vec4i UNIT_X = new ImplVec4i(1, 0, 0, 0);
    Vec4i UNIT_Y = new ImplVec4i(0, 1, 0, 0);
    Vec4i UNIT_Z = new ImplVec4i(0, 0, 1, 0);
    Vec4i UNIT_W = new ImplVec4i(0, 0, 0, 1);
    Vec4i ONE = new ImplVec4i(1, 1, 1, 1);

    static Vec4i of(final int n) {
        return n == 0 ? Vec4i.ZERO : new ImplVec4i(n, n, n, n);
    }

    static Vec4i of(final int x, final int y, final int z, final int w) {
        return x == 0 && y == 0 && z == 0 && w == 0 ? Vec4i.ZERO : new ImplVec4i(x, y, z, w);
    }

    int x();

    int y();

    int z();

    int w();

    Vec4i add(final Vec4i v);

    Vec4i add(final double x, final double y, final double z, final double w);

    Vec4i add(final int x, final int y, final int z, final int w);

    Vec4i sub(final Vec4i v);

    Vec4i sub(final double x, final double y, final double z, final double w);

    Vec4i sub(final int x, final int y, final int z, final int w);

    Vec4i mul(final double a);

    Vec4i mul(final int a);

    Vec4i mul(final Vec4i v);

    Vec4i mul(final double x, final double y, final double z, final double w);

    Vec4i mul(final int x, final int y, final int z, final int w);

    Vec4i div(final double a);

    Vec4i div(final int a);

    Vec4i div(final Vec4i v);

    Vec4i div(final double x, final double y, final double z, final double w);

    Vec4i div(final int x, final int y, final int z, final int w);

    int dot(final Vec4i v);

    int dot(final double x, final double y, final double z, final double w);

    int dot(final int x, final int y, final int z, final int w);

    Vec4i project(final Vec4i v);

    Vec4i project(final double x, final double y, final double z, final double w);

    Vec4i project(final int x, final int y, final int z, final int w);

    Vec4i pow(final double pow);

    Vec4i pow(final int power);

    Vec4i abs();

    Vec4i negate();

    Vec4i min(final Vec4i v);

    Vec4i min(final double x, final double y, final double z, final double w);

    Vec4i min(final int x, final int y, final int z, final int w);

    Vec4i max(final Vec4i v);

    Vec4i max(final double x, final double y, final double z, final double w);

    Vec4i max(final int x, final int y, final int z, final int w);

    int distanceSquared(final Vec4i v);

    int distanceSquared(final double x, final double y, final double z, final double w);

    int distanceSquared(final int x, final int y, final int z, final int w);

    float distance(final Vec4i v);

    float distance(final double x, final double y, final double z, final double w);

    float distance(final int x, final int y, final int z, final int w);

    int lengthSquared();

    float length();

    Vec2i toVec2();

    Vec3i toVec3();

    VecNi toVecN();

    int[] toArray();

    Vec4i toInt();

    Vec4l toLong();

    Vec4f toFloat();

    Vec4d toDouble();

    default MutableVec4i mut() {
        return new ImplMutableVec4i(x(), y(), z(), w());
    }

    default int axisCount() {
        return 4;
    }
}
