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

public interface Vec2i extends Vec, Comparable<Vec2i> {
    Vec2i ZERO = new ImplVec2i(0, 0);
    Vec2i UNIT_X = new ImplVec2i(1, 0);
    Vec2i UNIT_Y = new ImplVec2i(0, 1);
    Vec2i ONE = new ImplVec2i(1, 1);

    static Vec2i of(final int n) {
        return n == 0 ? Vec2i.ZERO : new ImplVec2i(n, n);
    }

    static Vec2i of(final int x, final int y) {
        return x == 0 && y == 0 ? Vec2i.ZERO : new ImplVec2i(x, y);
    }

    int x();

    int y();

    Vec2i add(final Vec2i v);

    Vec2i add(final double x, final double y);

    Vec2i add(final int x, final int y);

    Vec2i sub(final Vec2i v);

    Vec2i sub(final double x, final double y);

    Vec2i sub(final int x, final int y);

    Vec2i mul(final double a);

    Vec2i mul(final int a);

    Vec2i mul(final Vec2i v);

    Vec2i mul(final double x, final double y);

    Vec2i mul(final int x, final int y);

    Vec2i div(final double a);

    Vec2i div(final int a);

    Vec2i div(final Vec2i v);

    Vec2i div(final double x, final double y);

    Vec2i div(final int x, final int y);

    int dot(final Vec2i v);

    int dot(final double x, final double y);

    int dot(final int x, final int y);

    Vec2i project(final Vec2i v);

    Vec2i project(final double x, final double y);

    Vec2i project(final int x, final int y);

    Vec2i pow(final double pow);

    Vec2i pow(final int power);

    Vec2i abs();

    Vec2i negate();

    Vec2i min(final Vec2i v);

    Vec2i min(final double x, final double y);

    Vec2i min(final int x, final int y);

    Vec2i max(final Vec2i v);

    Vec2i max(final double x, final double y);

    Vec2i max(final int x, final int y);

    int distanceSquared(final Vec2i v);

    int distanceSquared(final double x, final double y);

    int distanceSquared(final int x, final int y);

    float distance(final Vec2i v);

    float distance(final double x, final double y);

    float distance(final int x, final int y);

    int lengthSquared();

    float length();

    Vec3i toVec3();

    Vec3i toVec3(final double z);

    Vec3i toVec3(final int z);

    Vec4i toVec4();

    Vec4i toVec4(final double z, final double w);

    Vec4i toVec4(final int z, final int w);

    VecNi toVecN();

    int[] toArray();

    Vec2i toInt();

    Vec2l toLong();

    Vec2f toFloat();

    Vec2d toDouble();

    default MutableVec2i mut() {
        return new ImplMutableVec2i(x(), y());
    }

    default int axisCount() {
        return 2;
    }
}
