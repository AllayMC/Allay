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

import cn.allay.api.math.GenericMath;

import java.util.Arrays;

public class VecNl implements Vec, Comparable<VecNl>, Cloneable {
    public static VecNl ZERO_2 = new ImmutableZeroVecN(0, 0);
    public static VecNl ZERO_3 = new ImmutableZeroVecN(0, 0, 0);
    public static VecNl ZERO_4 = new ImmutableZeroVecN(0, 0, 0, 0);
    private final long[] vec;

    public VecNl(final int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Minimum vector size is 2");
        }
        vec = new long[size];
    }

    public VecNl(final Vec2l v) {
        this(v.x(), v.y());
    }

    public VecNl(final Vec3l v) {
        this(v.x(), v.y(), v.z());
    }

    public VecNl(final Vec4l v) {
        this(v.x(), v.y(), v.z(), v.w());
    }

    public VecNl(final VecNl v) {
        this(v.vec);
    }

    public VecNl(final long... v) {
        this.vec = v.clone();
    }

    @Override
    public int axisCount() {
        return this.vec.length;
    }

    public long get(final int comp) {
        return this.vec[comp];
    }

    public void set(final int comp, final long val) {
        this.vec[comp] = val;
    }

    public void setZero() {
        Arrays.fill(vec, 0);
    }

    public VecNl resize(final int size) {
        final VecNl d = new VecNl(size);
        System.arraycopy(vec, 0, d.vec, 0, Math.min(size, this.axisCount()));
        return d;
    }

    public VecNl add(final VecNl v) {
        return this.add(v.vec);
    }

    public VecNl add(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] + v[comp];
        }
        return d;
    }

    public VecNl sub(final VecNl v) {
        return this.sub(v.vec);
    }

    public VecNl sub(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] - v[comp];
        }
        return d;
    }

    public VecNl mul(final double a) {
        return this.mul(GenericMath.floorl(a));
    }

    public VecNl mul(long a) {
        final int size = this.axisCount();
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * a;
        }
        return d;
    }

    public VecNl mul(final VecNl v) {
        return this.mul(v.vec);
    }

    public VecNl mul(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNl div(final double a) {
        return this.div(GenericMath.floorl(a));
    }

    public VecNl div(long a) {
        final int size = this.axisCount();
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = vec[comp] / a;
        }
        return d;
    }

    public VecNl div(final VecNl v) {
        return this.div(v.vec);
    }

    public VecNl div(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] / v[comp];
        }
        return d;
    }

    public long dot(final VecNl v) {
        return this.dot(v.vec);
    }

    public long dot(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        long d = 0;
        for (int comp = 0; comp < size; comp++) {
            d += this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNl project(final VecNl v) {
        return this.project(v.vec);
    }

    public VecNl project(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        long lengthSquared = 0;
        for (int comp = 0; comp < size; comp++) {
            lengthSquared += v[comp] * v[comp];
        }
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = (double) this.dot(v) / lengthSquared;
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = GenericMath.floorl(a * v[comp]);
        }
        return d;
    }

    public VecNl pow(final double pow) {
        return this.pow(GenericMath.floorl(pow));
    }

    public VecNl pow(final long power) {
        final int size = this.axisCount();
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = GenericMath.floorl(Math.pow(this.vec[comp], power));
        }
        return d;
    }

    public VecNl abs() {
        final int size = this.axisCount();
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.abs(this.vec[comp]);
        }
        return d;
    }

    public VecNl negate() {
        final int size = this.axisCount();
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = -this.vec[comp];
        }
        return d;
    }

    public VecNl min(final VecNl v) {
        return this.min(v.vec);
    }

    public VecNl min(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
        }
        return d;
    }

    public VecNl max(final VecNl v) {
        return this.max(v.vec);
    }

    public VecNl max(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNl d = new VecNl(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
        }
        return d;
    }

    public long distanceSquared(final VecNl v) {
        return distanceSquared(v.vec);
    }

    public long distanceSquared(final long... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        long d = 0;
        for (int comp = 0; comp < size; comp++) {
            final long delta = this.vec[comp] - v[comp];
            d += delta * delta;
        }
        return d;
    }

    public double distance(final VecNl v) {
        return this.distance(v.vec);
    }

    public double distance(final long... v) {
        return Math.sqrt(this.distanceSquared(v));
    }

    public long lengthSquared() {
        final int size = this.axisCount();
        long l = 0;
        for (int comp = 0; comp < size; comp++) {
            l += this.vec[comp] * this.vec[comp];
        }
        return l;
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    public int minAxis() {
        int axis = 0;
        long value = this.vec[axis];
        final int size = this.axisCount();
        for (int comp = 1; comp < size; comp++) {
            if (this.vec[comp] < value) {
                value = this.vec[comp];
                axis = comp;
            }
        }
        return axis;
    }

    @Override
    public int maxAxis() {
        int axis = 0;
        long value = vec[axis];
        final int size = this.axisCount();
        for (int comp = 1; comp < size; comp++) {
            if (this.vec[comp] > value) {
                value = this.vec[comp];
                axis = comp;
            }
        }
        return axis;
    }

    public Vec2l toVec2() {
        return new ImplVec2l(this);
    }

    public Vec3l toVec3() {
        return new ImplVec3l(this);
    }

    public Vec4l toVec4() {
        return new ImplVec4l(this);
    }

    public long[] toArray() {
        return this.vec.clone();
    }

    public VecNi toInt() {
        final int size = this.axisCount();
        final int[] intVec = new int[size];
        for (int comp = 0; comp < size; comp++) {
            intVec[comp] = (int) this.vec[comp];
        }
        return new VecNi(intVec);
    }

    public VecNl toLong() {
        final int size = this.axisCount();
        final long[] longVec = new long[size];
        for (int comp = 0; comp < size; comp++) {
            longVec[comp] = this.vec[comp];
        }
        return new VecNl(longVec);
    }

    public VecNf toFloat() {
        final int size = this.axisCount();
        final float[] floatVec = new float[size];
        for (int comp = 0; comp < size; comp++) {
            floatVec[comp] = (float) this.vec[comp];
        }
        return new VecNf(floatVec);
    }

    public VecNd toDouble() {
        final int size = this.axisCount();
        final double[] doubleVec = new double[size];
        for (int comp = 0; comp < size; comp++) {
            doubleVec[comp] = (double) this.vec[comp];
        }
        return new VecNd(doubleVec);
    }

    @Override
    public int compareTo(final VecNl v) {
        return Long.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof VecNl)) {
            return false;
        }
        return Arrays.equals(this.vec, ((VecNl) other).vec);
    }

    @Override
    public int hashCode() {
        return 67 * 5 + Arrays.hashCode(this.vec);
    }

    @Override
    public VecNl clone() {
        return new VecNl(this);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
    }

    private static class ImmutableZeroVecN extends VecNl {

        private static final long serialVersionUID = 1L;

        public ImmutableZeroVecN(final long... v) {
            super(v);
        }

        @Override
        public void set(final int comp, final long val) {
            throw new UnsupportedOperationException("You may not alter this vector");
        }

    }

}
