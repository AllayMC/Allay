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

public interface MutableVec4d extends Vec4d {
    void setX(final double x);

    void setY(final double y);

    void setZ(final double z);

    void setW(final double w);

    void setX(final float x);

    void setY(final float y);

    void setZ(final float z);

    void setW(final float w);

    void setVec4(final Vec4d vec4);

    MutableVec4d add(final Vec4d v);

    MutableVec4d add(final float x, final float y, final float z, final float w);

    MutableVec4d add(final double x, final double y, final double z, final double w);

    MutableVec4d sub(final Vec4d v);

    MutableVec4d sub(final float x, final float y, final float z, final float w);

    MutableVec4d sub(final double x, final double y, final double z, final double w);

    MutableVec4d mul(final float a);

    MutableVec4d mul(final double a);

    MutableVec4d mul(final Vec4d v);

    MutableVec4d mul(final float x, final float y, final float z, final float w);

    MutableVec4d mul(final double x, final double y, final double z, final double w);

    MutableVec4d div(final float a);

    MutableVec4d div(final double a);

    MutableVec4d div(final Vec4d v);

    MutableVec4d div(final float x, final float y, final float z, final float w);

    MutableVec4d div(final double x, final double y, final double z, final double w);

    MutableVec4d project(final Vec4d v);

    MutableVec4d project(final float x, final float y, final float z, final float w);

    MutableVec4d project(final double x, final double y, final double z, final double w);

    MutableVec4d pow(final float pow);

    MutableVec4d pow(final double power);

    MutableVec4d ceil();

    MutableVec4d floor();

    MutableVec4d round();

    MutableVec4d abs();

    MutableVec4d negate();

    MutableVec4d min(final Vec4d v);

    MutableVec4d min(final float x, final float y, final float z, final float w);

    MutableVec4d min(final double x, final double y, final double z, final double w);

    MutableVec4d max(final Vec4d v);

    MutableVec4d max(final float x, final float y, final float z, final float w);

    MutableVec4d max(final double x, final double y, final double z, final double w);

    MutableVec4d normalize();

    MutableVec2d toVec2();

    MutableVec3d toVec3();

    VecNd toVecN();

    default MutableVec4d mut() {
        return this;
    }

    MutableVec4i toInt();

    MutableVec4l toLong();

    MutableVec4f toFloat();

    MutableVec4d toDouble();
}