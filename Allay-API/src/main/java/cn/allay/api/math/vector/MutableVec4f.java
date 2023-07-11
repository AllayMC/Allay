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

public interface MutableVec4f extends Vec4f {
    void setX(final float x);

    void setY(final float y);

    void setZ(final float z);

    void setW(final float w);

    void setX(final double x);

    void setY(final double y);

    void setZ(final double z);

    void setW(final double w);

    void setVec4(final Vec4f vec4);

    MutableVec4f add(final Vec4f v);

    MutableVec4f add(final double x, final double y, final double z, final double w);

    MutableVec4f add(final float x, final float y, final float z, final float w);

    MutableVec4f sub(final Vec4f v);

    MutableVec4f sub(final double x, final double y, final double z, final double w);

    MutableVec4f sub(final float x, final float y, final float z, final float w);

    MutableVec4f mul(final double a);

    MutableVec4f mul(final float a);

    MutableVec4f mul(final Vec4f v);

    MutableVec4f mul(final double x, final double y, final double z, final double w);

    MutableVec4f mul(final float x, final float y, final float z, final float w);

    MutableVec4f div(final double a);

    MutableVec4f div(final float a);

    MutableVec4f div(final Vec4f v);

    MutableVec4f div(final double x, final double y, final double z, final double w);

    MutableVec4f div(final float x, final float y, final float z, final float w);

    MutableVec4f project(final Vec4f v);

    MutableVec4f project(final double x, final double y, final double z, final double w);

    MutableVec4f project(final float x, final float y, final float z, final float w);

    MutableVec4f pow(final double pow);

    MutableVec4f pow(final float power);

    MutableVec4f ceil();

    MutableVec4f floor();

    MutableVec4f round();

    MutableVec4f abs();

    MutableVec4f negate();

    MutableVec4f min(final Vec4f v);

    MutableVec4f min(final double x, final double y, final double z, final double w);

    MutableVec4f min(final float x, final float y, final float z, final float w);

    MutableVec4f max(final Vec4f v);

    MutableVec4f max(final double x, final double y, final double z, final double w);

    MutableVec4f max(final float x, final float y, final float z, final float w);

    MutableVec4f normalize();

    MutableVec2f toVec2();

    MutableVec3f toVec3();

    VecNf toVecN();

    default MutableVec4f mut() {
        return this;
    }

    MutableVec4i toInt();

    MutableVec4l toLong();

    MutableVec4f toFloat();

    MutableVec4d toDouble();
}