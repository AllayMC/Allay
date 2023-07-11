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

import cn.allay.api.world.World;

public interface Loc3d extends Pos3d {
    static Loc3d of(final double x, final double y, final double z, final double pitch, final double yaw, final double headYaw, final World world) {
        return new ImplLoc3d(x, y, z, pitch, yaw, headYaw, world);
    }

    double pitch();

    double yaw();

    double headYaw();

    Loc3d add(final Vec3d v);

    Loc3d add(final float x, final float y, final float z);

    Loc3d add(final double x, final double y, final double z);

    Loc3d sub(final Vec3d v);

    Loc3d sub(final float x, final float y, final float z);

    Loc3d sub(final double x, final double y, final double z);

    Loc3d mul(final float a);

    Loc3d mul(final double a);

    Loc3d mul(final Vec3d v);

    Loc3d mul(final float x, final float y, final float z);

    Loc3d mul(final double x, final double y, final double z);

    Loc3d div(final float a);

    Loc3d div(final double a);

    Loc3d div(final Vec3d v);

    Loc3d div(final float x, final float y, final float z);

    Loc3d div(final double x, final double y, final double z);

    Loc3d project(final Vec3d v);

    Loc3d project(final float x, final float y, final float z);

    Loc3d project(final double x, final double y, final double z);

    Loc3d cross(final Vec3d v);

    Loc3d cross(final float x, final float y, final float z);

    Loc3d cross(final double x, final double y, final double z);

    Loc3d pow(final float pow);

    Loc3d pow(final double power);

    Loc3d ceil();

    Loc3d floor();

    Loc3d round();

    Loc3d abs();

    Loc3d negate();

    Loc3d min(final Vec3d v);

    Loc3d min(final float x, final float y, final float z);

    Loc3d min(final double x, final double y, final double z);

    Loc3d max(final Vec3d v);

    Loc3d max(final float x, final float y, final float z);

    Loc3d max(final double x, final double y, final double z);

    Loc3d normalize();

    Loc3i toInt();

    Loc3l toLong();

    Loc3f toFloat();

    Loc3d toDouble();

    default MutableLoc3d mut() {
        return new ImplMutableLoc3d(x(), y(), z(), pitch(), yaw(), headYaw(), world());
    }
}
