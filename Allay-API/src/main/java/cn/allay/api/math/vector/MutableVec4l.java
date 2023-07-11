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

public interface MutableVec4l extends Vec4l {
    void setX(final long x);

    void setY(final long y);

    void setZ(final long z);

    void setW(final long w);

    void setVec4(final Vec4l vec4);

    MutableVec4l add(final Vec4l v);

    MutableVec4l add(final double x, final double y, final double z, final double w);

    MutableVec4l add(final long x, final long y, final long z, final long w);

    MutableVec4l sub(final Vec4l v);

    MutableVec4l sub(final double x, final double y, final double z, final double w);

    MutableVec4l sub(final long x, final long y, final long z, final long w);

    MutableVec4l mul(final double a);

    MutableVec4l mul(final long a);

    MutableVec4l mul(final Vec4l v);

    MutableVec4l mul(final double x, final double y, final double z, final double w);

    MutableVec4l mul(final long x, final long y, final long z, final long w);

    MutableVec4l div(final double a);

    MutableVec4l div(final long a);

    MutableVec4l div(final Vec4l v);

    MutableVec4l div(final double x, final double y, final double z, final double w);

    MutableVec4l div(final long x, final long y, final long z, final long w);

    MutableVec4l project(final Vec4l v);

    MutableVec4l project(final double x, final double y, final double z, final double w);

    MutableVec4l project(final long x, final long y, final long z, final long w);

    MutableVec4l pow(final double pow);

    MutableVec4l pow(final long power);

    MutableVec4l abs();

    MutableVec4l negate();

    MutableVec4l min(final Vec4l v);

    MutableVec4l min(final double x, final double y, final double z, final double w);

    MutableVec4l min(final long x, final long y, final long z, final long w);

    MutableVec4l max(final Vec4l v);

    MutableVec4l max(final double x, final double y, final double z, final double w);

    MutableVec4l max(final long x, final long y, final long z, final long w);

    MutableVec2l toVec2();

    MutableVec3l toVec3();

    VecNl toVecN();

    default MutableVec4l mut() {
        return this;
    }

    MutableVec4i toInt();

    MutableVec4l toLong();

    MutableVec4f toFloat();

    MutableVec4d toDouble();
}