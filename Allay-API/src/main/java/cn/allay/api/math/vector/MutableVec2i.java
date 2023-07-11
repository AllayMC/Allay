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

public interface MutableVec2i extends Vec2i {
    void setX(final int x);

    void setY(final int y);

    void setVec2(final Vec2i vec2);

    MutableVec2i add(final Vec2i v);

    MutableVec2i add(final double x, final double y);

    MutableVec2i add(final int x, final int y);

    MutableVec2i sub(final Vec2i v);

    MutableVec2i sub(final double x, final double y);

    MutableVec2i sub(final int x, final int y);

    MutableVec2i mul(final double a);

    MutableVec2i mul(final int a);

    MutableVec2i mul(final Vec2i v);

    MutableVec2i mul(final double x, final double y);

    MutableVec2i mul(final int x, final int y);

    MutableVec2i div(final double a);

    MutableVec2i div(final int a);

    MutableVec2i div(final Vec2i v);

    MutableVec2i div(final double x, final double y);

    MutableVec2i div(final int x, final int y);

    MutableVec2i project(final Vec2i v);

    MutableVec2i project(final double x, final double y);

    MutableVec2i project(final int x, final int y);

    MutableVec2i pow(final double pow);

    MutableVec2i pow(final int power);

    MutableVec2i abs();

    MutableVec2i negate();

    MutableVec2i min(final Vec2i v);

    MutableVec2i min(final double x, final double y);

    MutableVec2i min(final int x, final int y);

    MutableVec2i max(final Vec2i v);

    MutableVec2i max(final double x, final double y);

    MutableVec2i max(final int x, final int y);

    MutableVec3i toVec3();

    MutableVec3i toVec3(final double z);

    MutableVec3i toVec3(final int z);

    MutableVec4i toVec4();

    MutableVec4i toVec4(final double z, final double w);

    MutableVec4i toVec4(final int z, final int w);

    VecNi toVecN();

    default MutableVec2i mut() {
        return this;
    }

    MutableVec2i toInt();

    MutableVec2l toLong();

    MutableVec2f toFloat();

    MutableVec2d toDouble();
}