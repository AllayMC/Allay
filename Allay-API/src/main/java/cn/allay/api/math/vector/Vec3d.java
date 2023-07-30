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

public interface Vec3d extends Vec, Comparable<Vec3d> {
    Vec3d ZERO = new ImplVec3d(0, 0, 0);
    Vec3d UNIT_X = new ImplVec3d(1, 0, 0);
    Vec3d UNIT_Y = new ImplVec3d(0, 1, 0);
    Vec3d UNIT_Z = new ImplVec3d(0, 0, 1);
    Vec3d ONE = new ImplVec3d(1, 1, 1);
    Vec3d RIGHT = ImplVec3d.UNIT_X;
    Vec3d UP = ImplVec3d.UNIT_Y;
    Vec3d FORWARD = ImplVec3d.UNIT_Z;

    static Vec3d of(final double n) {
        return n == 0 ? Vec3d.ZERO : new ImplVec3d(n, n, n);
    }

    static Vec3d of(final double x, final double y, final double z) {
        return x == 0 && y == 0 && z == 0 ? Vec3d.ZERO : new ImplVec3d(x, y, z);
    }

    static Vec3d of(final float n) {
        return n == 0 ? Vec3d.ZERO : new ImplVec3d(n, n, (double) n);
    }

    static Vec3d of(final float x, final float y, final float z) {
        return x == 0 && y == 0 ? Vec3d.ZERO : new ImplVec3d(x, y, (double) z);
    }

    /**
     * Gets the direction vector of a random pitch and yaw using the random specified.
     *
     * @param random to use
     * @return the random direction vector
     */
    static Vec3d ofRandomDirection(final Random random) {
        return Vec3d.ofDirectionRad(random.nextDouble() * TrigMath.TWO_PI,
                random.nextDouble() * TrigMath.TWO_PI);
    }

    /**
     * Gets the direction vector of a certain theta and phi in degrees. This uses the standard math spherical coordinate system.
     *
     * @param theta in degrees
     * @param phi   in degrees
     * @return the direction vector
     */
    static Vec3d ofDirectionDeg(final float theta, final float phi) {
        return Vec3d.ofDirectionDeg(theta, (double) phi);
    }

    /**
     * Gets the direction vector of a certain theta and phi in degrees. This uses the standard math spherical coordinate system.
     *
     * @param theta in degrees
     * @param phi   in degrees
     * @return the direction vector
     */
    static Vec3d ofDirectionDeg(final double theta, final double phi) {
        return Vec3d.ofDirectionRad(Math.toRadians(theta), Math.toRadians(phi));
    }

    /**
     * Gets the direction vector of a certain theta and phi in radians. This uses the standard math spherical coordinate system.
     *
     * @param theta in radians
     * @param phi   in radians
     * @return the direction vector
     */
    static Vec3d ofDirectionRad(final float theta, final float phi) {
        return Vec3d.ofDirectionRad(theta, (double) phi);
    }

    /**
     * Gets the direction vector of a certain theta and phi in radians. This uses the standard math spherical coordinate system.
     *
     * @param theta in radians
     * @param phi   in radians
     * @return the direction vector
     */
    static Vec3d ofDirectionRad(final double theta, final double phi) {
        final double f = TrigMath.sin(phi);
        return new ImplVec3d(f * TrigMath.cos(theta), f * TrigMath.sin(theta), TrigMath.cos(phi));
    }

    double x();

    double y();

    double z();

    int floorX();

    int floorY();

    int floorZ();

    Vec3d add(final Vec3d v);

    Vec3d add(final float x, final float y, final float z);

    Vec3d add(final double x, final double y, final double z);

    Vec3d sub(final Vec3d v);

    Vec3d sub(final float x, final float y, final float z);

    Vec3d sub(final double x, final double y, final double z);

    Vec3d mul(final float a);

    Vec3d mul(final double a);

    Vec3d mul(final Vec3d v);

    Vec3d mul(final float x, final float y, final float z);

    Vec3d mul(final double x, final double y, final double z);

    Vec3d div(final float a);

    Vec3d div(final double a);

    Vec3d div(final Vec3d v);

    Vec3d div(final float x, final float y, final float z);

    Vec3d div(final double x, final double y, final double z);

    double dot(final Vec3d v);

    double dot(final float x, final float y, final float z);

    double dot(final double x, final double y, final double z);

    Vec3d project(final Vec3d v);

    Vec3d project(final float x, final float y, final float z);

    Vec3d project(final double x, final double y, final double z);

    Vec3d cross(final Vec3d v);

    Vec3d cross(final float x, final float y, final float z);

    Vec3d cross(final double x, final double y, final double z);

    Vec3d pow(final float pow);

    Vec3d pow(final double power);

    Vec3d ceil();

    Vec3d floor();

    Vec3d round();

    Vec3d abs();

    Vec3d negate();

    Vec3d min(final Vec3d v);

    Vec3d min(final float x, final float y, final float z);

    Vec3d min(final double x, final double y, final double z);

    Vec3d max(final Vec3d v);

    Vec3d max(final float x, final float y, final float z);

    Vec3d max(final double x, final double y, final double z);

    double distanceSquared(final Vec3d v);

    double distanceSquared(final float x, final float y, final float z);

    double distanceSquared(final double x, final double y, final double z);

    double distance(final Vec3d v);

    double distance(final float x, final float y, final float z);

    double distance(final double x, final double y, final double z);

    double lengthSquared();

    double length();

    Vec3d normalize();

    Vec2d toVec2();

    Vec2d toVec2(final boolean useZ);

    Vec4d toVec4();

    Vec4d toVec4(final float w);

    Vec4d toVec4(final double w);

    VecNd toVecN();

    double[] toArray();

    Vec3i toInt();

    Vec3l toLong();

    Vec3f toFloat();

    Vec3d toDouble();

    default MutableVec3d mut() {
        return new ImplMutableVec3d(x(), y(), z());
    }

    default int axisCount() {
        return 3;
    }
}
