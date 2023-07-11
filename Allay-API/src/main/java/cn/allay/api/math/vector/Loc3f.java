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

public interface Loc3f extends Pos3f {
    static Loc3f of(final float x, final float y, final float z, final double pitch, final double yaw, final double headYaw, final World world) {
        return new ImplLoc3f(x, y, z, pitch, yaw, headYaw, world);
    }

    double pitch();

    double yaw();

    double headYaw();

    Loc3f add(final Vec3f v);

    Loc3f add(final double x, final double y, final double z);

    Loc3f add(final float x, final float y, final float z);

    Loc3f sub(final Vec3f v);

    Loc3f sub(final double x, final double y, final double z);

    Loc3f sub(final float x, final float y, final float z);

    Loc3f mul(final double a);

    Loc3f mul(final float a);

    Loc3f mul(final Vec3f v);

    Loc3f mul(final double x, final double y, final double z);

    Loc3f mul(final float x, final float y, final float z);

    Loc3f div(final double a);

    Loc3f div(final float a);

    Loc3f div(final Vec3f v);

    Loc3f div(final double x, final double y, final double z);

    Loc3f div(final float x, final float y, final float z);

    Loc3f project(final Vec3f v);

    Loc3f project(final double x, final double y, final double z);

    Loc3f project(final float x, final float y, final float z);

    Loc3f cross(final Vec3f v);

    Loc3f cross(final double x, final double y, final double z);

    Loc3f cross(final float x, final float y, final float z);

    Loc3f pow(final double pow);

    Loc3f pow(final float power);

    Loc3f ceil();

    Loc3f floor();

    Loc3f round();

    Loc3f abs();

    Loc3f negate();

    Loc3f min(final Vec3f v);

    Loc3f min(final double x, final double y, final double z);

    Loc3f min(final float x, final float y, final float z);

    Loc3f max(final Vec3f v);

    Loc3f max(final double x, final double y, final double z);

    Loc3f max(final float x, final float y, final float z);

    Loc3f normalize();

    Loc3i toInt();

    Loc3l toLong();

    Loc3f toFloat();

    Loc3d toDouble();

    default MutableLoc3f mut() {
        return new ImplMutableLoc3f(x(), y(), z(), pitch(), yaw(), headYaw(), world());
    }
}
