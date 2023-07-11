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

public interface Vec2f extends Vec, Comparable<Vec2f> {
    Vec2f ZERO = new ImplVec2f(0, 0);
    Vec2f UNIT_X = new ImplVec2f(1, 0);
    Vec2f UNIT_Y = new ImplVec2f(0, 1);
    Vec2f ONE = new ImplVec2f(1, 1);

    static Vec2f of(final float n) {
        return n == 0 ? Vec2f.ZERO : new ImplVec2f(n, n);
    }

    static Vec2f of(final float x, final float y) {
        return x == 0 && y == 0 ? Vec2f.ZERO : new ImplVec2f(x, y);
    }

    static Vec2f of(final double n) {
        return n == 0 ? Vec2f.ZERO : new ImplVec2f((float) n, (float) n);
    }

    static Vec2f of(final double x, final double y) {
        return x == 0 && y == 0 ? Vec2f.ZERO : new ImplVec2f((float) x, (float) y);
    }

    /**
     * Gets the direction vector of a random angle using the random specified.
     *
     * @param random to use
     * @return the random direction vector
     */
    static Vec2f ofRandomDirection(final Random random) {
        return Vec2f.ofDirectionRad(random.nextFloat() * (float) TrigMath.TWO_PI);
    }

    /**
     * Gets the direction vector of a certain angle in degrees.
     *
     * @param angle in degrees
     * @return the direction vector
     */
    static Vec2f ofDirectionDeg(final double angle) {
        return Vec2f.ofDirectionDeg((float) angle);
    }

    /**
     * Gets the direction vector of a certain angle in degrees.
     *
     * @param angle in degrees
     * @return the direction vector
     */
    static Vec2f ofDirectionDeg(final float angle) {
        return Vec2f.ofDirectionRad((float) Math.toRadians(angle));
    }

    /**
     * Gets the direction vector of a certain angle in radians.
     *
     * @param angle in radians
     * @return the direction vector
     */
    static Vec2f ofDirectionRad(final double angle) {
        return Vec2f.ofDirectionRad((float) angle);
    }

    /**
     * Gets the direction vector of a certain angle in radians.
     *
     * @param angle in radians
     * @return the direction vector
     */
    static Vec2f ofDirectionRad(final float angle) {
        return new ImplVec2f(TrigMath.cos(angle), TrigMath.sin(angle));
    }

    float x();

    float y();

    int floorX();

    int floorY();

    Vec2f add(final Vec2f v);

    Vec2f add(final double x, final double y);

    Vec2f add(final float x, final float y);

    Vec2f sub(final Vec2f v);

    Vec2f sub(final double x, final double y);

    Vec2f sub(final float x, final float y);

    Vec2f mul(final double a);

    Vec2f mul(final float a);

    Vec2f mul(final Vec2f v);

    Vec2f mul(final double x, final double y);

    Vec2f mul(final float x, final float y);

    Vec2f div(final double a);

    Vec2f div(final float a);

    Vec2f div(final Vec2f v);

    Vec2f div(final double x, final double y);

    Vec2f div(final float x, final float y);

    float dot(final Vec2f v);

    float dot(final double x, final double y);

    float dot(final float x, final float y);

    Vec2f project(final Vec2f v);

    Vec2f project(final double x, final double y);

    Vec2f project(final float x, final float y);

    Vec2f pow(final double pow);

    Vec2f pow(final float power);

    Vec2f ceil();

    Vec2f floor();

    Vec2f round();

    Vec2f abs();

    Vec2f negate();

    Vec2f min(final Vec2f v);

    Vec2f min(final double x, final double y);

    Vec2f min(final float x, final float y);

    Vec2f max(final Vec2f v);

    Vec2f max(final double x, final double y);

    Vec2f max(final float x, final float y);

    float distanceSquared(final Vec2f v);

    float distanceSquared(final double x, final double y);

    float distanceSquared(final float x, final float y);

    float distance(final Vec2f v);

    float distance(final double x, final double y);

    float distance(final float x, final float y);

    float lengthSquared();

    float length();

    Vec2f normalize();

    Vec3f toVec3();

    Vec3f toVec3(final double z);

    Vec3f toVec3(final float z);

    Vec4f toVec4();

    Vec4f toVec4(final double z, final double w);

    Vec4f toVec4(final float z, final float w);

    VecNf toVecN();

    float[] toArray();

    Vec2i toInt();

    Vec2l toLong();

    Vec2f toFloat();

    Vec2d toDouble();

    default MutableVec2f mut() {
        return new ImplMutableVec2f(x(), y());
    }

    default int axisCount() {
        return 2;
    }
}
