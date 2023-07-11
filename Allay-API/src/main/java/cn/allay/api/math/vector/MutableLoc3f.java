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

public interface MutableLoc3f extends Loc3f, MutablePos3f {
    void setPitch(final double pitch);

    void setYaw(final double yaw);

    void setHeadYaw(final double headYaw);

    void setLocation(final Loc3f location);

    MutableLoc3f add(final Vec3f v);

    MutableLoc3f add(final double x, final double y, final double z);

    MutableLoc3f add(final float x, final float y, final float z);

    MutableLoc3f sub(final Vec3f v);

    MutableLoc3f sub(final double x, final double y, final double z);

    MutableLoc3f sub(final float x, final float y, final float z);

    MutableLoc3f mul(final double a);

    MutableLoc3f mul(final float a);

    MutableLoc3f mul(final Vec3f v);

    MutableLoc3f mul(final double x, final double y, final double z);

    MutableLoc3f mul(final float x, final float y, final float z);

    MutableLoc3f div(final double a);

    MutableLoc3f div(final float a);

    MutableLoc3f div(final Vec3f v);

    MutableLoc3f div(final double x, final double y, final double z);

    MutableLoc3f div(final float x, final float y, final float z);

    MutableLoc3f project(final Vec3f v);

    MutableLoc3f project(final double x, final double y, final double z);

    MutableLoc3f project(final float x, final float y, final float z);

    MutableLoc3f cross(final Vec3f v);

    MutableLoc3f cross(final double x, final double y, final double z);

    MutableLoc3f cross(final float x, final float y, final float z);

    MutableLoc3f pow(final double pow);

    MutableLoc3f pow(final float power);

    MutableLoc3f ceil();

    MutableLoc3f floor();

    MutableLoc3f round();

    MutableLoc3f abs();

    MutableLoc3f negate();

    MutableLoc3f min(final Vec3f v);

    MutableLoc3f min(final double x, final double y, final double z);

    MutableLoc3f min(final float x, final float y, final float z);

    MutableLoc3f max(final Vec3f v);

    MutableLoc3f max(final double x, final double y, final double z);

    MutableLoc3f max(final float x, final float y, final float z);

    MutableLoc3f normalize();

    default MutableLoc3f mut() {
        return this;
    }

    MutableLoc3i toInt();

    MutableLoc3l toLong();

    MutableLoc3f toFloat();

    MutableLoc3d toDouble();
}