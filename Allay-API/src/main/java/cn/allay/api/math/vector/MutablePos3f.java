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

import cn.allay.api.world.World;

public interface MutablePos3f extends Pos3f, MutableVec3f {
    void setWorld(final World world);

    void setPosition(final Pos3f position);

    MutablePos3f add(final Vec3f v);

    MutablePos3f add(final double x, final double y, final double z);

    MutablePos3f add(final float x, final float y, final float z);

    MutablePos3f sub(final Vec3f v);

    MutablePos3f sub(final double x, final double y, final double z);

    MutablePos3f sub(final float x, final float y, final float z);

    MutablePos3f mul(final double a);

    MutablePos3f mul(final float a);

    MutablePos3f mul(final Vec3f v);

    MutablePos3f mul(final double x, final double y, final double z);

    MutablePos3f mul(final float x, final float y, final float z);

    MutablePos3f div(final double a);

    MutablePos3f div(final float a);

    MutablePos3f div(final Vec3f v);

    MutablePos3f div(final double x, final double y, final double z);

    MutablePos3f div(final float x, final float y, final float z);

    MutablePos3f project(final Vec3f v);

    MutablePos3f project(final double x, final double y, final double z);

    MutablePos3f project(final float x, final float y, final float z);

    MutablePos3f cross(final Vec3f v);

    MutablePos3f cross(final double x, final double y, final double z);

    MutablePos3f cross(final float x, final float y, final float z);

    MutablePos3f pow(final double pow);

    MutablePos3f pow(final float power);

    MutablePos3f ceil();

    MutablePos3f floor();

    MutablePos3f round();

    MutablePos3f abs();

    MutablePos3f negate();

    MutablePos3f min(final Vec3f v);

    MutablePos3f min(final double x, final double y, final double z);

    MutablePos3f min(final float x, final float y, final float z);

    MutablePos3f max(final Vec3f v);

    MutablePos3f max(final double x, final double y, final double z);

    MutablePos3f max(final float x, final float y, final float z);

    MutablePos3f normalize();

    default MutablePos3f mut() {
        return this;
    }

    MutablePos3i toInt();

    MutablePos3l toLong();

    MutablePos3f toFloat();

    MutablePos3d toDouble();
}