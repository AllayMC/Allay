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

public class VecNi implements Vec, Comparable<VecNi>, Cloneable {
    public static VecNi ZERO_2 = new ImmutableZeroVecN(0, 0);
    public static VecNi ZERO_3 = new ImmutableZeroVecN(0, 0, 0);
    public static VecNi ZERO_4 = new ImmutableZeroVecN(0, 0, 0, 0);
    private final int[] vec;

    public VecNi(final int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Minimum vector size is 2");
        }
        vec = new int[size];
    }

    public VecNi(final Vec2i v) {
        this(v.x(), v.y());
    }

    public VecNi(final Vec3i v) {
        this(v.x(), v.y(), v.z());
    }

    public VecNi(final Vec4i v) {
        this(v.x(), v.y(), v.z(), v.w());
    }

    public VecNi(final VecNi v) {
        this(v.vec);
    }

    public VecNi(final int... v) {
        this.vec = v.clone();
    }

    @Override
    public int axisCount() {
        return this.vec.length;
    }

    public int get(final int comp) {
        return this.vec[comp];
    }

    public void set(final int comp, final int val) {
        this.vec[comp] = val;
    }

    public void setZero() {
        Arrays.fill(vec, 0);
    }

    public VecNi resize(final int size) {
        final VecNi d = new VecNi(size);
        System.arraycopy(vec, 0, d.vec, 0, Math.min(size, this.axisCount()));
        return d;
    }

    public VecNi add(final VecNi v) {
        return this.add(v.vec);
    }

    public VecNi add(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] + v[comp];
        }
        return d;
    }

    public VecNi sub(final VecNi v) {
        return this.sub(v.vec);
    }

    public VecNi sub(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] - v[comp];
        }
        return d;
    }

    public VecNi mul(final double a) {
        return this.mul(GenericMath.floor(a));
    }

    public VecNi mul(int a) {
        final int size = this.axisCount();
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * a;
        }
        return d;
    }

    public VecNi mul(final VecNi v) {
        return this.mul(v.vec);
    }

    public VecNi mul(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNi div(final double a) {
        return this.div(GenericMath.floor(a));
    }

    public VecNi div(int a) {
        final int size = this.axisCount();
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = vec[comp] / a;
        }
        return d;
    }

    public VecNi div(final VecNi v) {
        return this.div(v.vec);
    }

    public VecNi div(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] / v[comp];
        }
        return d;
    }

    public int dot(final VecNi v) {
        return this.dot(v.vec);
    }

    public int dot(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        int d = 0;
        for (int comp = 0; comp < size; comp++) {
            d += this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNi project(final VecNi v) {
        return this.project(v.vec);
    }

    public VecNi project(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        int lengthSquared = 0;
        for (int comp = 0; comp < size; comp++) {
            lengthSquared += v[comp] * v[comp];
        }
        if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final float a = (float) this.dot(v) / lengthSquared;
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = GenericMath.floor(a * v[comp]);
        }
        return d;
    }

    public VecNi pow(final double pow) {
        return this.pow(GenericMath.floor(pow));
    }

    public VecNi pow(final int power) {
        final int size = this.axisCount();
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = GenericMath.floor(Math.pow(this.vec[comp], power));
        }
        return d;
    }

    public VecNi abs() {
        final int size = this.axisCount();
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.abs(this.vec[comp]);
        }
        return d;
    }

    public VecNi negate() {
        final int size = this.axisCount();
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = -this.vec[comp];
        }
        return d;
    }

    public VecNi min(final VecNi v) {
        return this.min(v.vec);
    }

    public VecNi min(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
        }
        return d;
    }

    public VecNi max(final VecNi v) {
        return this.max(v.vec);
    }

    public VecNi max(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNi d = new VecNi(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
        }
        return d;
    }

    public int distanceSquared(final VecNi v) {
        return distanceSquared(v.vec);
    }

    public int distanceSquared(final int... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        int d = 0;
        for (int comp = 0; comp < size; comp++) {
            final int delta = this.vec[comp] - v[comp];
            d += delta * delta;
        }
        return d;
    }

    public float distance(final VecNi v) {
        return this.distance(v.vec);
    }

    public float distance(final int... v) {
        return (float) Math.sqrt(this.distanceSquared(v));
    }

    public int lengthSquared() {
        final int size = this.axisCount();
        int l = 0;
        for (int comp = 0; comp < size; comp++) {
            l += this.vec[comp] * this.vec[comp];
        }
        return l;
    }

    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    @Override
    public int minAxis() {
        int axis = 0;
        int value = this.vec[axis];
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
        int value = vec[axis];
        final int size = this.axisCount();
        for (int comp = 1; comp < size; comp++) {
            if (this.vec[comp] > value) {
                value = this.vec[comp];
                axis = comp;
            }
        }
        return axis;
    }

    public Vec2i toVec2() {
        return new ImplVec2i(this);
    }

    public Vec3i toVec3() {
        return new ImplVec3i(this);
    }

    public Vec4i toVec4() {
        return new ImplVec4i(this);
    }

    public int[] toArray() {
        return this.vec.clone();
    }

    public VecNi toInt() {
        final int size = this.axisCount();
        final int[] intVec = new int[size];
        for (int comp = 0; comp < size; comp++) {
            intVec[comp] = this.vec[comp];
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
            doubleVec[comp] = this.vec[comp];
        }
        return new VecNd(doubleVec);
    }

    @Override
    public int compareTo(final VecNi v) {
        return Integer.compare(this.lengthSquared(), v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof VecNi)) {
            return false;
        }
        return Arrays.equals(this.vec, ((VecNi) other).vec);
    }

    @Override
    public int hashCode() {
        return 67 * 5 + Arrays.hashCode(this.vec);
    }

    @Override
    public VecNi clone() {
        return new VecNi(this);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
    }

    private static class ImmutableZeroVecN extends VecNi {

        private static final long serialVersionUID = 1L;

        public ImmutableZeroVecN(final int... v) {
            super(v);
        }

        @Override
        public void set(final int comp, final int val) {
            throw new UnsupportedOperationException("You may not alter this vector");
        }

    }

}
