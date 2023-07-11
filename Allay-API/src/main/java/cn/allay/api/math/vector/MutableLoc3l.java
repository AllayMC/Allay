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

public interface MutableLoc3l extends Loc3l, MutablePos3l {
    void setPitch(final double pitch);

    void setYaw(final double yaw);

    void setHeadYaw(final double headYaw);

    void setLocation(final Loc3l location);

    MutableLoc3l add(final Vec3l v);

    MutableLoc3l add(final double x, final double y, final double z);

    MutableLoc3l add(final long x, final long y, final long z);

    MutableLoc3l sub(final Vec3l v);

    MutableLoc3l sub(final double x, final double y, final double z);

    MutableLoc3l sub(final long x, final long y, final long z);

    MutableLoc3l mul(final double a);

    MutableLoc3l mul(final long a);

    MutableLoc3l mul(final Vec3l v);

    MutableLoc3l mul(final double x, final double y, final double z);

    MutableLoc3l mul(final long x, final long y, final long z);

    MutableLoc3l div(final double a);

    MutableLoc3l div(final long a);

    MutableLoc3l div(final Vec3l v);

    MutableLoc3l div(final double x, final double y, final double z);

    MutableLoc3l div(final long x, final long y, final long z);

    MutableLoc3l project(final Vec3l v);

    MutableLoc3l project(final double x, final double y, final double z);

    MutableLoc3l project(final long x, final long y, final long z);

    MutableLoc3l cross(final Vec3l v);

    MutableLoc3l cross(final double x, final double y, final double z);

    MutableLoc3l cross(final long x, final long y, final long z);

    MutableLoc3l pow(final double pow);

    MutableLoc3l pow(final long power);

    MutableLoc3l abs();

    MutableLoc3l negate();

    MutableLoc3l min(final Vec3l v);

    MutableLoc3l min(final double x, final double y, final double z);

    MutableLoc3l min(final long x, final long y, final long z);

    MutableLoc3l max(final Vec3l v);

    MutableLoc3l max(final double x, final double y, final double z);

    MutableLoc3l max(final long x, final long y, final long z);

    MutableLoc3i toInt();

    MutableLoc3l toLong();

    MutableLoc3f toFloat();

    MutableLoc3d toDouble();

    default MutableLoc3l mut() {
        return this;
    }
}