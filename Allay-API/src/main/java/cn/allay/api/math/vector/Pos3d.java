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

public interface Pos3d extends Vec3d {
    public static Pos3d of(final double x, final double y, final double z, final World world) {
        return new ImplPos3d(x, y, z, world);
    }

    World world();

    Pos3d add(final Vec3d v);

    Pos3d add(final float x, final float y, final float z);

    Pos3d add(final double x, final double y, final double z);

    Pos3d sub(final Vec3d v);

    Pos3d sub(final float x, final float y, final float z);

    Pos3d sub(final double x, final double y, final double z);

    Pos3d mul(final float a);

    Pos3d mul(final double a);

    Pos3d mul(final Vec3d v);

    Pos3d mul(final float x, final float y, final float z);

    Pos3d mul(final double x, final double y, final double z);

    Pos3d div(final float a);

    Pos3d div(final double a);

    Pos3d div(final Vec3d v);

    Pos3d div(final float x, final float y, final float z);

    Pos3d div(final double x, final double y, final double z);

    Pos3d project(final Vec3d v);

    Pos3d project(final float x, final float y, final float z);

    Pos3d project(final double x, final double y, final double z);

    Pos3d cross(final Vec3d v);

    Pos3d cross(final float x, final float y, final float z);

    Pos3d cross(final double x, final double y, final double z);

    Pos3d pow(final float pow);

    Pos3d pow(final double power);

    Pos3d ceil();

    Pos3d floor();

    Pos3d round();

    Pos3d abs();

    Pos3d negate();

    Pos3d min(final Vec3d v);

    Pos3d min(final float x, final float y, final float z);

    Pos3d min(final double x, final double y, final double z);

    Pos3d max(final Vec3d v);

    Pos3d max(final float x, final float y, final float z);

    Pos3d max(final double x, final double y, final double z);

    Pos3d normalize();

    Pos3i toInt();

    Pos3l toLong();

    Pos3f toFloat();

    Pos3d toDouble();

    default MutablePos3d mut() {
        return new ImplMutablePos3d(x(), y(), z(), world());
    }
}
