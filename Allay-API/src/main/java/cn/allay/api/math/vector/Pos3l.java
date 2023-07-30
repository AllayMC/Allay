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

public interface Pos3l extends Vec3l {
    static Pos3l of(final long x, final long y, final long z, final World world) {
        return new ImplPos3l(x, y, z, world);
    }

    World world();

    Pos3l add(final Vec3l v);

    Pos3l add(final double x, final double y, final double z);

    Pos3l add(final long x, final long y, final long z);

    Pos3l sub(final Vec3l v);

    Pos3l sub(final double x, final double y, final double z);

    Pos3l sub(final long x, final long y, final long z);

    Pos3l mul(final double a);

    Pos3l mul(final long a);

    Pos3l mul(final Vec3l v);

    Pos3l mul(final double x, final double y, final double z);

    Pos3l mul(final long x, final long y, final long z);

    Pos3l div(final double a);

    Pos3l div(final long a);

    Pos3l div(final Vec3l v);

    Pos3l div(final double x, final double y, final double z);

    Pos3l div(final long x, final long y, final long z);

    Pos3l project(final Vec3l v);

    Pos3l project(final double x, final double y, final double z);

    Pos3l project(final long x, final long y, final long z);

    Pos3l cross(final Vec3l v);

    Pos3l cross(final double x, final double y, final double z);

    Pos3l cross(final long x, final long y, final long z);

    Pos3l pow(final double pow);

    Pos3l pow(final long power);

    Pos3l abs();

    Pos3l negate();

    Pos3l min(final Vec3l v);

    Pos3l min(final double x, final double y, final double z);

    Pos3l min(final long x, final long y, final long z);

    Pos3l max(final Vec3l v);

    Pos3l max(final double x, final double y, final double z);

    Pos3l max(final long x, final long y, final long z);

    Pos3i toInt();

    Pos3l toLong();

    Pos3f toFloat();

    Pos3d toDouble();

    default MutablePos3l mut() {
        return new ImplMutablePos3l(x(), y(), z(), world());
    }
}
