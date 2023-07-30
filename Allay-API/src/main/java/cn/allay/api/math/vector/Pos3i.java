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

public interface Pos3i extends Vec3i {
    static Pos3i of(final int x, final int y, final int z, final World world) {
        return new ImplPos3i(x, y, z, world);
    }

    World world();

    Pos3i add(final Vec3i v);

    Pos3i add(final double x, final double y, final double z);

    Pos3i add(final int x, final int y, final int z);

    Pos3i sub(final Vec3i v);

    Pos3i sub(final double x, final double y, final double z);

    Pos3i sub(final int x, final int y, final int z);

    Pos3i mul(final double a);

    Pos3i mul(final int a);

    Pos3i mul(final Vec3i v);

    Pos3i mul(final double x, final double y, final double z);

    Pos3i mul(final int x, final int y, final int z);

    Pos3i div(final double a);

    Pos3i div(final int a);

    Pos3i div(final Vec3i v);

    Pos3i div(final double x, final double y, final double z);

    Pos3i div(final int x, final int y, final int z);

    Pos3i project(final Vec3i v);

    Pos3i project(final double x, final double y, final double z);

    Pos3i project(final int x, final int y, final int z);

    Pos3i cross(final Vec3i v);

    Pos3i cross(final double x, final double y, final double z);

    Pos3i cross(final int x, final int y, final int z);

    Pos3i pow(final double pow);

    Pos3i pow(final int power);

    Pos3i abs();

    Pos3i negate();

    Pos3i min(final Vec3i v);

    Pos3i min(final double x, final double y, final double z);

    Pos3i min(final int x, final int y, final int z);

    Pos3i max(final Vec3i v);

    Pos3i max(final double x, final double y, final double z);

    Pos3i max(final int x, final int y, final int z);

    Pos3i toInt();

    Pos3l toLong();

    Pos3f toFloat();

    Pos3d toDouble();

    default MutablePos3i mut() {
        return new ImplMutablePos3i(x(), y(), z(), world());
    }
}
