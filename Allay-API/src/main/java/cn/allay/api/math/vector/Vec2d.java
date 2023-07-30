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

import cn.allay.api.math.TrigMath;

import java.util.Random;

public interface Vec2d extends Vec, Comparable<Vec2d> {
    Vec2d ZERO = new ImplVec2d(0, 0);
    Vec2d UNIT_X = new ImplVec2d(1, 0);
    Vec2d UNIT_Y = new ImplVec2d(0, 1);
    Vec2d ONE = new ImplVec2d(1, 1);

    static Vec2d of(final double n) {
        return n == 0 ? Vec2d.ZERO : new ImplVec2d(n, n);
    }

    static Vec2d of(final double x, final double y) {
        return x == 0 && y == 0 ? Vec2d.ZERO : new ImplVec2d(x, y);
    }

    static Vec2d of(final float n) {
        return n == 0 ? Vec2d.ZERO : new ImplVec2d(n, (double) n);
    }

    static Vec2d of(final float x, final float y) {
        return x == 0 && y == 0 ? Vec2d.ZERO : new ImplVec2d(x, (double) y);
    }

    /**
     * Gets the direction vector of a random angle using the random specified.
     *
     * @param random to use
     * @return the random direction vector
     */
    static Vec2d ofRandomDirection(final Random random) {
        return Vec2d.ofDirectionRad(random.nextDouble() * TrigMath.TWO_PI);
    }

    /**
     * Gets the direction vector of a certain angle in degrees.
     *
     * @param angle in degrees
     * @return the direction vector
     */
    static Vec2d ofDirectionDeg(final float angle) {
        return Vec2d.ofDirectionDeg((double) angle);
    }

    /**
     * Gets the direction vector of a certain angle in degrees.
     *
     * @param angle in degrees
     * @return the direction vector
     */
    static Vec2d ofDirectionDeg(final double angle) {
        return Vec2d.ofDirectionRad(Math.toRadians(angle));
    }

    /**
     * Gets the direction vector of a certain angle in radians.
     *
     * @param angle in radians
     * @return the direction vector
     */
    static Vec2d ofDirectionRad(final float angle) {
        return Vec2d.ofDirectionRad((double) angle);
    }

    /**
     * Gets the direction vector of a certain angle in radians.
     *
     * @param angle in radians
     * @return the direction vector
     */
    static Vec2d ofDirectionRad(final double angle) {
        return new ImplVec2d(TrigMath.cos(angle), TrigMath.sin(angle));
    }

    double x();

    double y();

    int floorX();

    int floorY();

    Vec2d add(final Vec2d v);

    Vec2d add(final float x, final float y);

    Vec2d add(final double x, final double y);

    Vec2d sub(final Vec2d v);

    Vec2d sub(final float x, final float y);

    Vec2d sub(final double x, final double y);

    Vec2d mul(final float a);

    Vec2d mul(final double a);

    Vec2d mul(final Vec2d v);

    Vec2d mul(final float x, final float y);

    Vec2d mul(final double x, final double y);

    Vec2d div(final float a);

    Vec2d div(final double a);

    Vec2d div(final Vec2d v);

    Vec2d div(final float x, final float y);

    Vec2d div(final double x, final double y);

    double dot(final Vec2d v);

    double dot(final float x, final float y);

    double dot(final double x, final double y);

    Vec2d project(final Vec2d v);

    Vec2d project(final float x, final float y);

    Vec2d project(final double x, final double y);

    Vec2d pow(final float pow);

    Vec2d pow(final double power);

    Vec2d ceil();

    Vec2d floor();

    Vec2d round();

    Vec2d abs();

    Vec2d negate();

    Vec2d min(final Vec2d v);

    Vec2d min(final float x, final float y);

    Vec2d min(final double x, final double y);

    Vec2d max(final Vec2d v);

    Vec2d max(final float x, final float y);

    Vec2d max(final double x, final double y);

    double distanceSquared(final Vec2d v);

    double distanceSquared(final float x, final float y);

    double distanceSquared(final double x, final double y);

    double distance(final Vec2d v);

    double distance(final float x, final float y);

    double distance(final double x, final double y);

    double lengthSquared();

    double length();

    Vec2d normalize();

    Vec3d toVec3();

    Vec3d toVec3(final float z);

    Vec3d toVec3(final double z);

    Vec4d toVec4();

    Vec4d toVec4(final float z, final float w);

    Vec4d toVec4(final double z, final double w);

    VecNd toVecN();

    double[] toArray();

    Vec2i toInt();

    Vec2l toLong();

    Vec2f toFloat();

    Vec2d toDouble();

    default MutableVec2d mut() {
        return new ImplMutableVec2d(x(), y());
    }

    default int axisCount() {
        return 2;
    }
}
