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

public interface MutableVec3f extends Vec3f {
    void setX(final float x);

    void setY(final float y);

    void setZ(final float z);

    void setX(final double x);

    void setY(final double y);

    void setZ(final double z);

    void setVec3(final Vec3f vec3);

    MutableVec3f add(final Vec3f v);

    MutableVec3f add(final double x, final double y, final double z);

    MutableVec3f add(final float x, final float y, final float z);

    MutableVec3f sub(final Vec3f v);

    MutableVec3f sub(final double x, final double y, final double z);

    MutableVec3f sub(final float x, final float y, final float z);

    MutableVec3f mul(final double a);

    MutableVec3f mul(final float a);

    MutableVec3f mul(final Vec3f v);

    MutableVec3f mul(final double x, final double y, final double z);

    MutableVec3f mul(final float x, final float y, final float z);

    MutableVec3f div(final double a);

    MutableVec3f div(final float a);

    MutableVec3f div(final Vec3f v);

    MutableVec3f div(final double x, final double y, final double z);

    MutableVec3f div(final float x, final float y, final float z);

    MutableVec3f project(final Vec3f v);

    MutableVec3f project(final double x, final double y, final double z);

    MutableVec3f project(final float x, final float y, final float z);

    MutableVec3f cross(final Vec3f v);

    MutableVec3f cross(final double x, final double y, final double z);

    MutableVec3f cross(final float x, final float y, final float z);

    MutableVec3f pow(final double pow);

    MutableVec3f pow(final float power);

    MutableVec3f ceil();

    MutableVec3f floor();

    MutableVec3f round();

    MutableVec3f abs();

    MutableVec3f negate();

    MutableVec3f min(final Vec3f v);

    MutableVec3f min(final double x, final double y, final double z);

    MutableVec3f min(final float x, final float y, final float z);

    MutableVec3f max(final Vec3f v);

    MutableVec3f max(final double x, final double y, final double z);

    MutableVec3f max(final float x, final float y, final float z);

    MutableVec3f normalize();

    MutableVec2f toVec2();

    MutableVec2f toVec2(final boolean useZ);

    MutableVec4f toVec4();

    MutableVec4f toVec4(final double w);

    MutableVec4f toVec4(final float w);

    VecNf toVecN();

    default MutableVec3f mut() {
        return this;
    }

    MutableVec3i toInt();

    MutableVec3l toLong();

    MutableVec3f toFloat();

    MutableVec3d toDouble();
}