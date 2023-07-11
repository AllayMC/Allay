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

public interface Vec4f extends Vec, Comparable<Vec4f> {
    Vec4f ZERO = new ImplVec4f(0, 0, 0, 0);
    Vec4f UNIT_X = new ImplVec4f(1, 0, 0, 0);
    Vec4f UNIT_Y = new ImplVec4f(0, 1, 0, 0);
    Vec4f UNIT_Z = new ImplVec4f(0, 0, 1, 0);
    Vec4f UNIT_W = new ImplVec4f(0, 0, 0, 1);
    Vec4f ONE = new ImplVec4f(1, 1, 1, 1);

    static Vec4f of(final float n) {
        return n == 0 ? Vec4f.ZERO : new ImplVec4f(n, n, n, n);
    }

    static Vec4f of(final float x, final float y, final float z, final float w) {
        return x == 0 && y == 0 && z == 0 && w == 0 ? Vec4f.ZERO : new ImplVec4f(x, y, z, w);
    }

    static Vec4f of(final double n) {
        return n == 0 ? Vec4f.ZERO : new ImplVec4f((float) n, (float) n, (float) n, (float) n);
    }

    static Vec4f of(final double x, final double y, final double z, final double w) {
        return x == 0 && y == 0 ? Vec4f.ZERO : new ImplVec4f((float) x, (float) y, (float) z, (float) w);
    }

    float x();

    float y();

    float z();

    float w();

    int floorX();

    int floorY();

    int floorZ();

    int floorW();

    Vec4f add(final Vec4f v);

    Vec4f add(final double x, final double y, final double z, final double w);

    Vec4f add(final float x, final float y, final float z, final float w);

    Vec4f sub(final Vec4f v);

    Vec4f sub(final double x, final double y, final double z, final double w);

    Vec4f sub(final float x, final float y, final float z, final float w);

    Vec4f mul(final double a);

    Vec4f mul(final float a);

    Vec4f mul(final Vec4f v);

    Vec4f mul(final double x, final double y, final double z, final double w);

    Vec4f mul(final float x, final float y, final float z, final float w);

    Vec4f div(final double a);

    Vec4f div(final float a);

    Vec4f div(final Vec4f v);

    Vec4f div(final double x, final double y, final double z, final double w);

    Vec4f div(final float x, final float y, final float z, final float w);

    float dot(final Vec4f v);

    float dot(final double x, final double y, final double z, final double w);

    float dot(final float x, final float y, final float z, final float w);

    Vec4f project(final Vec4f v);

    Vec4f project(final double x, final double y, final double z, final double w);

    Vec4f project(final float x, final float y, final float z, final float w);

    Vec4f pow(final double pow);

    Vec4f pow(final float power);

    Vec4f ceil();

    Vec4f floor();

    Vec4f round();

    Vec4f abs();

    Vec4f negate();

    Vec4f min(final Vec4f v);

    Vec4f min(final double x, final double y, final double z, final double w);

    Vec4f min(final float x, final float y, final float z, final float w);

    Vec4f max(final Vec4f v);

    Vec4f max(final double x, final double y, final double z, final double w);

    Vec4f max(final float x, final float y, final float z, final float w);

    float distanceSquared(final Vec4f v);

    float distanceSquared(final double x, final double y, final double z, final double w);

    float distanceSquared(final float x, final float y, final float z, final float w);

    float distance(final Vec4f v);

    float distance(final double x, final double y, final double z, final double w);

    float distance(final float x, final float y, final float z, final float w);

    float lengthSquared();

    float length();

    Vec4f normalize();

    Vec2f toVec2();

    Vec3f toVec3();

    VecNf toVecN();

    float[] toArray();

    Vec4i toInt();

    Vec4l toLong();

    Vec4f toFloat();

    Vec4d toDouble();

    default MutableVec4f mut() {
        return new ImplMutableVec4f(x(), y(), z(), w());
    }

    default int axisCount() {
        return 4;
    }
}
