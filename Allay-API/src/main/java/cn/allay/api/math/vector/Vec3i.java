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

public interface Vec3i extends Vec, Comparable<Vec3i> {
    Vec3i ZERO = new ImplVec3i(0, 0, 0);
    Vec3i UNIT_X = new ImplVec3i(1, 0, 0);
    Vec3i UNIT_Y = new ImplVec3i(0, 1, 0);
    Vec3i UNIT_Z = new ImplVec3i(0, 0, 1);
    Vec3i ONE = new ImplVec3i(1, 1, 1);
    Vec3i RIGHT = ImplVec3i.UNIT_X;
    Vec3i UP = ImplVec3i.UNIT_Y;
    Vec3i FORWARD = ImplVec3i.UNIT_Z;

    static Vec3i of(final int n) {
        return n == 0 ? Vec3i.ZERO : new ImplVec3i(n, n, n);
    }

    static Vec3i of(final int x, final int y, final int z) {
        return x == 0 && y == 0 && z == 0 ? Vec3i.ZERO : new ImplVec3i(x, y, z);
    }

    int x();

    int y();

    int z();

    Vec3i add(final Vec3i v);

    Vec3i add(final double x, final double y, final double z);

    Vec3i add(final int x, final int y, final int z);

    Vec3i sub(final Vec3i v);

    Vec3i sub(final double x, final double y, final double z);

    Vec3i sub(final int x, final int y, final int z);

    Vec3i mul(final double a);

    Vec3i mul(final int a);

    Vec3i mul(final Vec3i v);

    Vec3i mul(final double x, final double y, final double z);

    Vec3i mul(final int x, final int y, final int z);

    Vec3i div(final double a);

    Vec3i div(final int a);

    Vec3i div(final Vec3i v);

    Vec3i div(final double x, final double y, final double z);

    Vec3i div(final int x, final int y, final int z);

    int dot(final Vec3i v);

    int dot(final double x, final double y, final double z);

    int dot(final int x, final int y, final int z);

    Vec3i project(final Vec3i v);

    Vec3i project(final double x, final double y, final double z);

    Vec3i project(final int x, final int y, final int z);

    Vec3i cross(final Vec3i v);

    Vec3i cross(final double x, final double y, final double z);

    Vec3i cross(final int x, final int y, final int z);

    Vec3i pow(final double pow);

    Vec3i pow(final int power);

    Vec3i abs();

    Vec3i negate();

    Vec3i min(final Vec3i v);

    Vec3i min(final double x, final double y, final double z);

    Vec3i min(final int x, final int y, final int z);

    Vec3i max(final Vec3i v);

    Vec3i max(final double x, final double y, final double z);

    Vec3i max(final int x, final int y, final int z);

    int distanceSquared(final Vec3i v);

    int distanceSquared(final double x, final double y, final double z);

    int distanceSquared(final int x, final int y, final int z);

    float distance(final Vec3i v);

    float distance(final double x, final double y, final double z);

    float distance(final int x, final int y, final int z);

    int lengthSquared();

    float length();

    Vec2i toVec2();

    Vec2i toVec2(final boolean useZ);

    Vec4i toVec4();

    Vec4i toVec4(final double w);

    Vec4i toVec4(final int w);

    VecNi toVecN();

    int[] toArray();

    Vec3i toInt();

    Vec3l toLong();

    Vec3f toFloat();

    Vec3d toDouble();

    default MutableVec3i mut() {
        return new ImplMutableVec3i(x(), y(), z());
    }

    default int axisCount() {
        return 3;
    }
}
