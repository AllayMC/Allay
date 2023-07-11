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

public class VecNd implements Vec, Comparable<VecNd>, Cloneable {
    public static VecNd ZERO_2 = new ImmutableZeroVecN(0, 0);
    public static VecNd ZERO_3 = new ImmutableZeroVecN(0, 0, 0);
    public static VecNd ZERO_4 = new ImmutableZeroVecN(0, 0, 0, 0);
    private final double[] vec;

    public VecNd(final int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Minimum vector size is 2");
        }
        vec = new double[size];
    }

    public VecNd(final Vec2d v) {
        this(v.x(), v.y());
    }

    public VecNd(final Vec3d v) {
        this(v.x(), v.y(), v.z());
    }

    public VecNd(final Vec4d v) {
        this(v.x(), v.y(), v.z(), v.w());
    }

    public VecNd(final VecNd v) {
        this(v.vec);
    }

    public VecNd(final double... v) {
        this.vec = v.clone();
    }

    @Override
    public int axisCount() {
        return this.vec.length;
    }

    public double get(final int comp) {
        return this.vec[comp];
    }

    public int floored(final int comp) {
        return GenericMath.floor(this.get(comp));
    }

    public void set(final int comp, final float val) {
        this.set(comp, (double) val);
    }

    public void set(final int comp, final double val) {
        this.vec[comp] = val;
    }

    public void setZero() {
        Arrays.fill(this.vec, 0);
    }

    public VecNd resize(final int size) {
        final VecNd d = new VecNd(size);
        System.arraycopy(vec, 0, d.vec, 0, Math.min(size, this.axisCount()));
        return d;
    }

    public VecNd add(final VecNd v) {
        return this.add(v.vec);
    }

    public VecNd add(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] + v[comp];
        }
        return d;
    }

    public VecNd sub(final VecNd v) {
        return this.sub(v.vec);
    }

    public VecNd sub(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] - v[comp];
        }
        return d;
    }

    public VecNd mul(final float a) {
        return this.mul((double) a);
    }

    public VecNd mul(final double a) {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * a;
        }
        return d;
    }

    public VecNd mul(final VecNd v) {
        return this.mul(v.vec);
    }

    public VecNd mul(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNd div(final float a) {
        return this.div((double) a);
    }

    public VecNd div(double a) {
        final int size = axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = vec[comp] / a;
        }
        return d;
    }

    public VecNd div(final VecNd v) {
        return this.div(v.vec);
    }

    public VecNd div(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = vec[comp] / v[comp];
        }
        return d;
    }

    public double dot(final VecNd v) {
        return this.dot(v.vec);
    }

    public double dot(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        double d = 0;
        for (int comp = 0; comp < size; comp++) {
            d += this.vec[comp] * v[comp];
        }
        return d;
    }

    public VecNd project(final VecNd v) {
        return this.project(v.vec);
    }

    public VecNd project(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        double lengthSquared = 0;
        for (int comp = 0; comp < size; comp++) {
            lengthSquared += v[comp] * v[comp];
        }
        if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vec");
        }
        final double a = this.dot(v) / lengthSquared;
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = a * v[comp];
        }
        return d;
    }

    public VecNd pow(final float pow) {
        return this.pow((double) pow);
    }

    public VecNd pow(final double power) {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = (double) Math.pow(this.vec[comp], power);
        }
        return d;
    }

    public VecNd ceil() {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = (double) Math.ceil(this.vec[comp]);
        }
        return d;
    }

    public VecNd floor() {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = GenericMath.floor(this.vec[comp]);
        }
        return d;
    }

    public VecNd round() {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.round(this.vec[comp]);
        }
        return d;
    }

    public VecNd abs() {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.abs(this.vec[comp]);
        }
        return d;
    }

    public VecNd negate() {
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = -this.vec[comp];
        }
        return d;
    }

    public VecNd min(final VecNd v) {
        return this.min(v.vec);
    }

    public VecNd min(final double... v) {
        final int size = axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
        }
        return d;
    }

    public VecNd max(final VecNd v) {
        return this.max(v.vec);
    }

    public VecNd max(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
        }
        return d;
    }

    public double distanceSquared(final VecNd v) {
        return this.distanceSquared(v.vec);
    }

    public double distanceSquared(final double... v) {
        final int size = this.axisCount();
        if (size != v.length) {
            throw new IllegalArgumentException("Vec sizes must be the same");
        }
        double d = 0;
        for (int comp = 0; comp < size; comp++) {
            final double delta = this.vec[comp] - v[comp];
            d += delta * delta;
        }
        return d;
    }

    public double distance(final VecNd v) {
        return this.distance(v.vec);
    }

    public double distance(final double... v) {
        return (double) Math.sqrt(this.distanceSquared(v));
    }

    public double lengthSquared() {
        final int size = this.axisCount();
        double l = 0;
        for (int comp = 0; comp < size; comp++) {
            l += this.vec[comp] * this.vec[comp];
        }
        return l;
    }

    public double length() {
        return (double) Math.sqrt(this.lengthSquared());
    }

    public VecNd normalize() {
        final double length = this.length();
        if (Math.abs(length) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot normalize the zero vec");
        }
        final int size = this.axisCount();
        final VecNd d = new VecNd(size);
        for (int comp = 0; comp < size; comp++) {
            d.vec[comp] = (double) (this.vec[comp] / length);
        }
        return d;
    }

    @Override
    public int minAxis() {
        int axis = 0;
        double value = this.vec[axis];
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
        double value = this.vec[axis];
        final int size = this.axisCount();
        for (int comp = 1; comp < size; comp++) {
            if (this.vec[comp] > value) {
                value = this.vec[comp];
                axis = comp;
            }
        }
        return axis;
    }

    public Vec2d toVec2() {
        return new ImplVec2d(this);
    }

    public Vec3d toVec3() {
        return new ImplVec3d(this);
    }

    public Vec4d toVec4() {
        return new ImplVec4d(this);
    }

    public double[] toArray() {
        return vec.clone();
    }

    public VecNi toInt() {
        final int size = this.axisCount();
        final int[] intVec = new int[size];
        for (int comp = 0; comp < size; comp++) {
            intVec[comp] = GenericMath.floor(this.vec[comp]);
        }
        return new VecNi(intVec);
    }

    public VecNl toLong() {
        final int size = this.axisCount();
        final long[] longVec = new long[size];
        for (int comp = 0; comp < size; comp++) {
            longVec[comp] = GenericMath.floorl(this.vec[comp]);
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
    public int compareTo(final VecNd v) {
        return (int) Math.signum(this.lengthSquared() - v.lengthSquared());
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof VecNd)) {
            return false;
        }
        return Arrays.equals(this.vec, ((VecNd) other).vec);
    }

    @Override
    public int hashCode() {
        return 67 * 5 + Arrays.hashCode(this.vec);
    }

    @Override
    public VecNd clone() {
        return new VecNd(this);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
    }

    private static class ImmutableZeroVecN extends VecNd {

        private static final long serialVersionUID = 1L;

        public ImmutableZeroVecN(final double... v) {
            super(v);
        }

        @Override
        public void set(final int comp, final double val) {
            throw new UnsupportedOperationException("You may not alter this vector");
        }
    }
}
