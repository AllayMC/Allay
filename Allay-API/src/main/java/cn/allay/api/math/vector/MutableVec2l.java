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

public interface MutableVec2l extends Vec2l {
    void setX(final long x);

    void setY(final long y);

    void setVec2(final Vec2l vec2);

    MutableVec2l add(final Vec2l v);

    MutableVec2l add(final double x, final double y);

    MutableVec2l add(final long x, final long y);

    MutableVec2l sub(final Vec2l v);

    MutableVec2l sub(final double x, final double y);

    MutableVec2l sub(final long x, final long y);

    MutableVec2l mul(final double a);

    MutableVec2l mul(final long a);

    MutableVec2l mul(final Vec2l v);

    MutableVec2l mul(final double x, final double y);

    MutableVec2l mul(final long x, final long y);

    MutableVec2l div(final double a);

    MutableVec2l div(final long a);

    MutableVec2l div(final Vec2l v);

    MutableVec2l div(final double x, final double y);

    MutableVec2l div(final long x, final long y);

    MutableVec2l project(final Vec2l v);

    MutableVec2l project(final double x, final double y);

    MutableVec2l project(final long x, final long y);

    MutableVec2l pow(final double pow);

    MutableVec2l pow(final long power);

    MutableVec2l abs();

    MutableVec2l negate();

    MutableVec2l min(final Vec2l v);

    MutableVec2l min(final double x, final double y);

    MutableVec2l min(final long x, final long y);

    MutableVec2l max(final Vec2l v);

    MutableVec2l max(final double x, final double y);

    MutableVec2l max(final long x, final long y);

    MutableVec3l toVec3();

    MutableVec3l toVec3(final double z);

    MutableVec3l toVec3(final long z);

    MutableVec4l toVec4();

    MutableVec4l toVec4(final double z, final double w);

    MutableVec4l toVec4(final long z, final long w);

    VecNl toVecN();

    default MutableVec2l mut() {
        return this;
    }

    MutableVec2i toInt();

    MutableVec2l toLong();

    MutableVec2f toFloat();

    MutableVec2d toDouble();
}