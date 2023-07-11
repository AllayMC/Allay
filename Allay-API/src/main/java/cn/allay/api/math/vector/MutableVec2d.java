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

public interface MutableVec2d extends Vec2d {
    void setX(final double x);

    void setY(final double y);

    void setX(final float x);

    void setY(final float y);

    void setVec2(final Vec2d vec2);

    MutableVec2d add(final Vec2d v);

    MutableVec2d add(final float x, final float y);

    MutableVec2d add(final double x, final double y);

    MutableVec2d sub(final Vec2d v);

    MutableVec2d sub(final float x, final float y);

    MutableVec2d sub(final double x, final double y);

    MutableVec2d mul(final float a);

    MutableVec2d mul(final double a);

    MutableVec2d mul(final Vec2d v);

    MutableVec2d mul(final float x, final float y);

    MutableVec2d mul(final double x, final double y);

    MutableVec2d div(final float a);

    MutableVec2d div(final double a);

    MutableVec2d div(final Vec2d v);

    MutableVec2d div(final float x, final float y);

    MutableVec2d div(final double x, final double y);

    MutableVec2d project(final Vec2d v);

    MutableVec2d project(final float x, final float y);

    MutableVec2d project(final double x, final double y);

    MutableVec2d pow(final float pow);

    MutableVec2d pow(final double power);

    MutableVec2d ceil();

    MutableVec2d floor();

    MutableVec2d round();

    MutableVec2d abs();

    MutableVec2d negate();

    MutableVec2d min(final Vec2d v);

    MutableVec2d min(final float x, final float y);

    MutableVec2d min(final double x, final double y);

    MutableVec2d max(final Vec2d v);

    MutableVec2d max(final float x, final float y);

    MutableVec2d max(final double x, final double y);

    MutableVec2d normalize();

    MutableVec3d toVec3();

    MutableVec3d toVec3(final float z);

    MutableVec3d toVec3(final double z);

    MutableVec4d toVec4();

    MutableVec4d toVec4(final float z, final float w);

    MutableVec4d toVec4(final double z, final double w);

    VecNd toVecN();

    default MutableVec2d mut() {
        return this;
    }

    MutableVec2i toInt();

    MutableVec2l toLong();

    MutableVec2f toFloat();

    MutableVec2d toDouble();
}