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

public interface MutableVec3d extends Vec3d {
    void setX(final double x);

    void setY(final double y);

    void setZ(final double z);

    void setX(final float x);

    void setY(final float y);

    void setZ(final float z);

    void setVec3(final Vec3d vec3);

    MutableVec3d add(final Vec3d v);

    MutableVec3d add(final float x, final float y, final float z);

    MutableVec3d add(final double x, final double y, final double z);

    MutableVec3d sub(final Vec3d v);

    MutableVec3d sub(final float x, final float y, final float z);

    MutableVec3d sub(final double x, final double y, final double z);

    MutableVec3d mul(final float a);

    MutableVec3d mul(final double a);

    MutableVec3d mul(final Vec3d v);

    MutableVec3d mul(final float x, final float y, final float z);

    MutableVec3d mul(final double x, final double y, final double z);

    MutableVec3d div(final float a);

    MutableVec3d div(final double a);

    MutableVec3d div(final Vec3d v);

    MutableVec3d div(final float x, final float y, final float z);

    MutableVec3d div(final double x, final double y, final double z);

    MutableVec3d project(final Vec3d v);

    MutableVec3d project(final float x, final float y, final float z);

    MutableVec3d project(final double x, final double y, final double z);

    MutableVec3d cross(final Vec3d v);

    MutableVec3d cross(final float x, final float y, final float z);

    MutableVec3d cross(final double x, final double y, final double z);

    MutableVec3d pow(final float pow);

    MutableVec3d pow(final double power);

    MutableVec3d ceil();

    MutableVec3d floor();

    MutableVec3d round();

    MutableVec3d abs();

    MutableVec3d negate();

    MutableVec3d min(final Vec3d v);

    MutableVec3d min(final float x, final float y, final float z);

    MutableVec3d min(final double x, final double y, final double z);

    MutableVec3d max(final Vec3d v);

    MutableVec3d max(final float x, final float y, final float z);

    MutableVec3d max(final double x, final double y, final double z);

    MutableVec3d normalize();

    MutableVec2d toVec2();

    MutableVec2d toVec2(final boolean useZ);

    MutableVec4d toVec4();

    MutableVec4d toVec4(final float w);

    MutableVec4d toVec4(final double w);

    VecNd toVecN();

    default MutableVec3d mut() {
        return this;
    }

    MutableVec3i toInt();

    MutableVec3l toLong();

    MutableVec3f toFloat();

    MutableVec3d toDouble();
}