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

public interface MutableVec2f extends Vec2f {
    void setX(final float x);

    void setY(final float y);

    void setX(final double x);

    void setY(final double y);

    void setVec2(final Vec2f vec2);

    MutableVec2f add(final Vec2f v);

    MutableVec2f add(final double x, final double y);

    MutableVec2f add(final float x, final float y);

    MutableVec2f sub(final Vec2f v);

    MutableVec2f sub(final double x, final double y);

    MutableVec2f sub(final float x, final float y);

    MutableVec2f mul(final double a);

    MutableVec2f mul(final float a);

    MutableVec2f mul(final Vec2f v);

    MutableVec2f mul(final double x, final double y);

    MutableVec2f mul(final float x, final float y);

    MutableVec2f div(final double a);

    MutableVec2f div(final float a);

    MutableVec2f div(final Vec2f v);

    MutableVec2f div(final double x, final double y);

    MutableVec2f div(final float x, final float y);

    MutableVec2f project(final Vec2f v);

    MutableVec2f project(final double x, final double y);

    MutableVec2f project(final float x, final float y);

    MutableVec2f pow(final double pow);

    MutableVec2f pow(final float power);

    MutableVec2f ceil();

    MutableVec2f floor();

    MutableVec2f round();

    MutableVec2f abs();

    MutableVec2f negate();

    MutableVec2f min(final Vec2f v);

    MutableVec2f min(final double x, final double y);

    MutableVec2f min(final float x, final float y);

    MutableVec2f max(final Vec2f v);

    MutableVec2f max(final double x, final double y);

    MutableVec2f max(final float x, final float y);

    MutableVec2f normalize();

    MutableVec3f toVec3();

    MutableVec3f toVec3(final double z);

    MutableVec3f toVec3(final float z);

    MutableVec4f toVec4();

    MutableVec4f toVec4(final double z, final double w);

    MutableVec4f toVec4(final float z, final float w);

    VecNf toVecN();

    default MutableVec2f mut() {
        return this;
    }

    MutableVec2i toInt();

    MutableVec2l toLong();

    MutableVec2f toFloat();

    MutableVec2d toDouble();
}