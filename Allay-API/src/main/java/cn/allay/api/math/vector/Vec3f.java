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

public interface Vec3f extends Vec, Comparable<Vec3f> {
    Vec3f ZERO = new ImplVec3f(0, 0, 0);
    Vec3f UNIT_X = new ImplVec3f(1, 0, 0);
    Vec3f UNIT_Y = new ImplVec3f(0, 1, 0);
    Vec3f UNIT_Z = new ImplVec3f(0, 0, 1);
    Vec3f ONE = new ImplVec3f(1, 1, 1);
    Vec3f RIGHT = ImplVec3f.UNIT_X;
    Vec3f UP = ImplVec3f.UNIT_Y;
    Vec3f FORWARD = ImplVec3f.UNIT_Z;

    static Vec3f of(final float n) {
        return n == 0 ? Vec3f.ZERO : new ImplVec3f(n, n, n);
    }

    static Vec3f of(final float x, final float y, final float z) {
        return x == 0 && y == 0 && z == 0 ? Vec3f.ZERO : new ImplVec3f(x, y, z);
    }

    static Vec3f of(final double n) {
        return n == 0 ? Vec3f.ZERO : new ImplVec3f((float) n, (float) n, (float) n);
    }

    static Vec3f of(final double x, final double y, final double z) {
        return x == 0 && y == 0 ? Vec3f.ZERO : new ImplVec3f((float) x, (float) y, (float) z);
    }

    /**
     * Gets the direction vector of a random pitch and yaw using the random specified.
     *
     * @param random to use
     * @return the random direction vector
     */
    static Vec3f ofRandomDirection(final Random random) {
        return Vec3f.ofDirectionRad(random.nextFloat() * (float) TrigMath.TWO_PI,
                random.nextFloat() * (float) TrigMath.TWO_PI);
    }

    /**
     * Gets the direction vector of a certain theta and phi in degrees. This uses the standard math spherical coordinate system.
     *
     * @param theta in degrees
     * @param phi   in degrees
     * @return the direction vector
     */
    static Vec3f ofDirectionDeg(final double theta, final double phi) {
        return Vec3f.ofDirectionDeg((float) theta, (float) phi);
    }

    /**
     * Gets the direction vector of a certain theta and phi in degrees. This uses the standard math spherical coordinate system.
     *
     * @param theta in degrees
     * @param phi   in degrees
     * @return the direction vector
     */
    static Vec3f ofDirectionDeg(final float theta, final float phi) {
        return Vec3f.ofDirectionRad((float) Math.toRadians(theta), (float) Math.toRadians(phi));
    }

    /**
     * Gets the direction vector of a certain theta and phi in radians. This uses the standard math spherical coordinate system.
     *
     * @param theta in radians
     * @param phi   in radians
     * @return the direction vector
     */
    static Vec3f ofDirectionRad(final double theta, final double phi) {
        return Vec3f.ofDirectionRad((float) theta, (float) phi);
    }

    /**
     * Gets the direction vector of a certain theta and phi in radians. This uses the standard math spherical coordinate system.
     *
     * @param theta in radians
     * @param phi   in radians
     * @return the direction vector
     */
    static Vec3f ofDirectionRad(final float theta, final float phi) {
        final float f = TrigMath.sin(phi);
        return new ImplVec3f(f * TrigMath.cos(theta), f * TrigMath.sin(theta), TrigMath.cos(phi));
    }

    float x();

    float y();

    float z();

    int floorX();

    int floorY();

    int floorZ();

    Vec3f add(final Vec3f v);

    Vec3f add(final double x, final double y, final double z);

    Vec3f add(final float x, final float y, final float z);

    Vec3f sub(final Vec3f v);

    Vec3f sub(final double x, final double y, final double z);

    Vec3f sub(final float x, final float y, final float z);

    Vec3f mul(final double a);

    Vec3f mul(final float a);

    Vec3f mul(final Vec3f v);

    Vec3f mul(final double x, final double y, final double z);

    Vec3f mul(final float x, final float y, final float z);

    Vec3f div(final double a);

    Vec3f div(final float a);

    Vec3f div(final Vec3f v);

    Vec3f div(final double x, final double y, final double z);

    Vec3f div(final float x, final float y, final float z);

    float dot(final Vec3f v);

    float dot(final double x, final double y, final double z);

    float dot(final float x, final float y, final float z);

    Vec3f project(final Vec3f v);

    Vec3f project(final double x, final double y, final double z);

    Vec3f project(final float x, final float y, final float z);

    Vec3f cross(final Vec3f v);

    Vec3f cross(final double x, final double y, final double z);

    Vec3f cross(final float x, final float y, final float z);

    Vec3f pow(final double pow);

    Vec3f pow(final float power);

    Vec3f ceil();

    Vec3f floor();

    Vec3f round();

    Vec3f abs();

    Vec3f negate();

    Vec3f min(final Vec3f v);

    Vec3f min(final double x, final double y, final double z);

    Vec3f min(final float x, final float y, final float z);

    Vec3f max(final Vec3f v);

    Vec3f max(final double x, final double y, final double z);

    Vec3f max(final float x, final float y, final float z);

    float distanceSquared(final Vec3f v);

    float distanceSquared(final double x, final double y, final double z);

    float distanceSquared(final float x, final float y, final float z);

    float distance(final Vec3f v);

    float distance(final double x, final double y, final double z);

    float distance(final float x, final float y, final float z);

    float lengthSquared();

    float length();

    Vec3f normalize();

    Vec2f toVec2();

    Vec2f toVec2(final boolean useZ);

    Vec4f toVec4();

    Vec4f toVec4(final double w);

    Vec4f toVec4(final float w);

    VecNf toVecN();

    float[] toArray();

    Vec3i toInt();

    Vec3l toLong();

    Vec3f toFloat();

    Vec3d toDouble();

    default MutableVec3f mut() {
        return new ImplMutableVec3f(x(), y(), z());
    }

    default int axisCount() {
        return 3;
    }
}
