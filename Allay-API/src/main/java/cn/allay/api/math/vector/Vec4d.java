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

public interface Vec4d extends Vec, Comparable<Vec4d> {
    Vec4d ZERO = new ImplVec4d(0, 0, 0, 0);
    Vec4d UNIT_X = new ImplVec4d(1, 0, 0, 0);
    Vec4d UNIT_Y = new ImplVec4d(0, 1, 0, 0);
    Vec4d UNIT_Z = new ImplVec4d(0, 0, 1, 0);
    Vec4d UNIT_W = new ImplVec4d(0, 0, 0, 1);
    Vec4d ONE = new ImplVec4d(1, 1, 1, 1);

    static Vec4d of(final double n) {
        return n == 0 ? Vec4d.ZERO : new ImplVec4d(n, n, n, n);
    }

    static Vec4d of(final double x, final double y, final double z, final double w) {
        return x == 0 && y == 0 && z == 0 && w == 0 ? Vec4d.ZERO : new ImplVec4d(x, y, z, w);
    }

    static Vec4d of(final float n) {
        return n == 0 ? Vec4d.ZERO : new ImplVec4d(n, n, n, (double) n);
    }

    static Vec4d of(final float x, final float y, final float z, final float w) {
        return x == 0 && y == 0 ? Vec4d.ZERO : new ImplVec4d(x, y, z, (double) w);
    }

    double x();

    double y();

    double z();

    double w();

    int floorX();

    int floorY();

    int floorZ();

    int floorW();

    Vec4d add(final Vec4d v);

    Vec4d add(final float x, final float y, final float z, final float w);

    Vec4d add(final double x, final double y, final double z, final double w);

    Vec4d sub(final Vec4d v);

    Vec4d sub(final float x, final float y, final float z, final float w);

    Vec4d sub(final double x, final double y, final double z, final double w);

    Vec4d mul(final float a);

    Vec4d mul(final double a);

    Vec4d mul(final Vec4d v);

    Vec4d mul(final float x, final float y, final float z, final float w);

    Vec4d mul(final double x, final double y, final double z, final double w);

    Vec4d div(final float a);

    Vec4d div(final double a);

    Vec4d div(final Vec4d v);

    Vec4d div(final float x, final float y, final float z, final float w);

    Vec4d div(final double x, final double y, final double z, final double w);

    double dot(final Vec4d v);

    double dot(final float x, final float y, final float z, final float w);

    double dot(final double x, final double y, final double z, final double w);

    Vec4d project(final Vec4d v);

    Vec4d project(final float x, final float y, final float z, final float w);

    Vec4d project(final double x, final double y, final double z, final double w);

    Vec4d pow(final float pow);

    Vec4d pow(final double power);

    Vec4d ceil();

    Vec4d floor();

    Vec4d round();

    Vec4d abs();

    Vec4d negate();

    Vec4d min(final Vec4d v);

    Vec4d min(final float x, final float y, final float z, final float w);

    Vec4d min(final double x, final double y, final double z, final double w);

    Vec4d max(final Vec4d v);

    Vec4d max(final float x, final float y, final float z, final float w);

    Vec4d max(final double x, final double y, final double z, final double w);

    double distanceSquared(final Vec4d v);

    double distanceSquared(final float x, final float y, final float z, final float w);

    double distanceSquared(final double x, final double y, final double z, final double w);

    double distance(final Vec4d v);

    double distance(final float x, final float y, final float z, final float w);

    double distance(final double x, final double y, final double z, final double w);

    double lengthSquared();

    double length();

    Vec4d normalize();

    Vec2d toVec2();

    Vec3d toVec3();

    VecNd toVecN();

    double[] toArray();

    Vec4i toInt();

    Vec4l toLong();

    Vec4f toFloat();

    Vec4d toDouble();

    default MutableVec4d mut() {
        return new ImplMutableVec4d(x(), y(), z(), w());
    }

    default int axisCount() {
        return 4;
    }
}
