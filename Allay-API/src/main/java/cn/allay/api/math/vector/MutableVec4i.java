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

public interface MutableVec4i extends Vec4i {
    void setX(final int x);

    void setY(final int y);

    void setZ(final int z);

    void setW(final int w);

    void setVec4(final Vec4i vec4);

    MutableVec4i add(final Vec4i v);

    MutableVec4i add(final double x, final double y, final double z, final double w);

    MutableVec4i add(final int x, final int y, final int z, final int w);

    MutableVec4i sub(final Vec4i v);

    MutableVec4i sub(final double x, final double y, final double z, final double w);

    MutableVec4i sub(final int x, final int y, final int z, final int w);

    MutableVec4i mul(final double a);

    MutableVec4i mul(final int a);

    MutableVec4i mul(final Vec4i v);

    MutableVec4i mul(final double x, final double y, final double z, final double w);

    MutableVec4i mul(final int x, final int y, final int z, final int w);

    MutableVec4i div(final double a);

    MutableVec4i div(final int a);

    MutableVec4i div(final Vec4i v);

    MutableVec4i div(final double x, final double y, final double z, final double w);

    MutableVec4i div(final int x, final int y, final int z, final int w);

    MutableVec4i project(final Vec4i v);

    MutableVec4i project(final double x, final double y, final double z, final double w);

    MutableVec4i project(final int x, final int y, final int z, final int w);

    MutableVec4i pow(final double pow);

    MutableVec4i pow(final int power);

    MutableVec4i abs();

    MutableVec4i negate();

    MutableVec4i min(final Vec4i v);

    MutableVec4i min(final double x, final double y, final double z, final double w);

    MutableVec4i min(final int x, final int y, final int z, final int w);

    MutableVec4i max(final Vec4i v);

    MutableVec4i max(final double x, final double y, final double z, final double w);

    MutableVec4i max(final int x, final int y, final int z, final int w);

    MutableVec2i toVec2();

    MutableVec3i toVec3();

    VecNi toVecN();

    default MutableVec4i mut() {
        return this;
    }

    MutableVec4i toInt();

    MutableVec4l toLong();

    MutableVec4f toFloat();

    MutableVec4d toDouble();
}