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
package cn.allay.api.math;

import com.google.errorprone.annotations.CheckReturnValue;

/**
 * A class designed for fast trigonometry operations. Sine, cosine, tangent, cotangent secant and cosecant use a sine float table.
 */
@CheckReturnValue
public final class TrigMath {
    // Constants
    public static final double PI = Math.PI;
    public static final double SQUARED_PI = PI * PI;
    public static final double HALF_PI = PI / 2;
    public static final double QUARTER_PI = HALF_PI / 2;
    public static final double TWO_PI = 2 * PI;
    public static final double THREE_PI_HALVES = TWO_PI - HALF_PI;
    public static final double DEG_TO_RAD = PI / 180;
    public static final double HALF_DEG_TO_RAD = PI / 360;
    public static final double RAD_TO_DEG = 180 / PI;
    public static final double SQRT_OF_TWO = Math.sqrt(2);
    public static final double HALF_SQRT_OF_TWO = SQRT_OF_TWO / 2;
    // Trig
    private static final int SIN_BITS = 22;
    private static final int SIN_SIZE = 1 << SIN_BITS;
    private static final int SIN_MASK = SIN_SIZE - 1;
    private static final float[] SIN_TABLE = new float[SIN_SIZE];
    private static final double SIN_CONVERSION_FACTOR = (SIN_SIZE / TWO_PI);
    private static final int COS_OFFSET = SIN_SIZE / 4;
    // Arc trig
    private static final double sq2p1 = 2.414213562373095048802;
    private static final double sq2m1 = 0.414213562373095048802;
    private static final double p4 = 0.161536412982230228262E2;
    private static final double p3 = 0.26842548195503973794141E3;
    private static final double p2 = 0.11530293515404850115428136E4;
    private static final double p1 = 0.178040631643319697105464587E4;
    private static final double p0 = 0.89678597403663861959987488E3;
    private static final double q4 = 0.5895697050844462222791E2;
    private static final double q3 = 0.536265374031215315104235E3;
    private static final double q2 = 0.16667838148816337184521798E4;
    private static final double q1 = 0.207933497444540981287275926E4;
    private static final double q0 = 0.89678597403663861962481162E3;

    static {
        for (int i = 0; i < SIN_SIZE; i++) {
            SIN_TABLE[i] = (float) Math.sin((i * TWO_PI) / SIN_SIZE);
        }
    }

    private TrigMath() {
    }

    /**
     * Sine calculation using a table.
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle the angle in radians
     * @return the sine of the angle
     */
    public static float sin(double angle) {
        return sinRaw(GenericMath.floor(angle * SIN_CONVERSION_FACTOR));
    }

    /**
     * Cosine calculation using a table.
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle the angle in radians
     * @return the cosine of the angle
     */
    public static float cos(double angle) {
        return cosRaw(GenericMath.floor(angle * SIN_CONVERSION_FACTOR));
    }

    /**
     * Tangent calculations using a table.<br> <i>sin(angle) / cos(angle)</i>
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle in radians
     * @return the tangent of the angle
     */
    public static float tan(double angle) {
        int idx = GenericMath.floor(angle * SIN_CONVERSION_FACTOR);
        return sinRaw(idx) / cosRaw(idx);
    }

    /**
     * Cosecant calculations using a table.<br> <i>1 / sin(angle)</i>
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle the angle in radians
     * @return the cosecant of the angle
     */
    public static float csc(double angle) {
        return 1 / sin(angle);
    }

    /**
     * Secant calculations using a table:<br> <i>1 / cos(angle)</i>
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle the angle in radians
     * @return the secant of the angle
     */
    public static float sec(double angle) {
        return 1 / cos(angle);
    }

    /**
     * Cotangent calculations using a table.<br> <i>cos(angle) / sin(angle)</i>
     *
     * <p><b>No interpolation is performed:</b> Accuracy is up to the 6th decimal place.</p>
     *
     * @param angle in radians
     * @return the cotangent of the angle
     */
    public static float cot(double angle) {
        int idx = GenericMath.floor(angle * SIN_CONVERSION_FACTOR);
        return cosRaw(idx) / sinRaw(idx);
    }

    /**
     * Calculates the arc sine of the value specified<br><br> Returns NaN if the input value is outside the sine range
     *
     * @param value of the sine
     * @return sine arc in radians
     */
    public static double asin(double value) {
        if (value > 1) {
            return Double.NaN;
        } else if (value < 0) {
            return -asin(-value);
        } else {
            double temp = Math.sqrt(1 - value * value);
            if (value > 0.7) {
                return HALF_PI - msatan(temp / value);
            } else {
                return msatan(value / temp);
            }
        }
    }

    /**
     * Calculates the arc cosine of the value specified<br><br> Returns NaN if the input value is outside the cosine range
     *
     * @param value of the cosine
     * @return cosine arc in radians
     */
    public static double acos(double value) {
        if (value > 1 || value < -1) {
            return Double.NaN;
        } else {
            return HALF_PI - asin(value);
        }
    }

    /**
     * Calculates the arc tangent of the value specified
     *
     * @param value of the tangent
     * @return tangent arc in radians
     */
    public static double atan(double value) {
        if (value > 0) {
            return msatan(value);
        } else {
            return -msatan(-value);
        }
    }

    /**
     * Computes the phase theta by computing an arc tangent of y/x<br> Gets the yaw rotation component in radians when looking into the direction specified
     *
     * @param y direction
     * @param x direction
     * @return tangent arc in radians
     */
    public static double atan2(double y, double x) {
        if (y + x == y) {
            return y >= 0 ? HALF_PI : -HALF_PI;
        }
        y = atan(y / x);
        if (x < 0) {
            if (y <= 0) {
                return y + PI;
            } else {
                return y - PI;
            }
        }
        return y;
    }

    /**
     * Calculates the arc cosecant of the value specified<br><br> Returns NaN if the input value is outside the cosecant range
     *
     * @param value of the cosecant
     * @return cosecant arc in radians
     */
    public static double acsc(double value) {
        if (value == 0) {
            return Double.NaN;
        }
        return asin(1 / value);
    }

    /**
     * Calculates the arc secant of the value specified<br><br> Returns NaN if the input value is outside the secant range
     *
     * @param value of the secant
     * @return secant arc in radians
     */
    public static double asec(double value) {
        if (value == 0) {
            return Double.NaN;
        }
        return acos(1 / value);
    }

    /**
     * Calculates the arc cotangent of the value specified<br><br> Returns NaN if the input value is outside the cotangent range
     *
     * @param value of the cotangent
     * @return cotangent arc in radians
     */
    public static double acot(double value) {
        if (value == 0) {
            return Double.NaN;
        } else if (value > 0) {
            return atan(1 / value);
        } else {
            return atan(1 / value) + PI;
        }
    }

    private static float sinRaw(int idx) {
        return SIN_TABLE[idx & SIN_MASK];
    }

    private static float cosRaw(int idx) {
        return SIN_TABLE[(idx + COS_OFFSET) & SIN_MASK];
    }

    private static double mxatan(double arg) {
        final double argsq = arg * arg;
        double value = ((((p4 * argsq + p3) * argsq + p2) * argsq + p1) * argsq + p0);
        value /= ((((argsq + q4) * argsq + q3) * argsq + q2) * argsq + q1) * argsq + q0;
        return value * arg;
    }

    private static double msatan(double arg) {
        if (arg < sq2m1) {
            return mxatan(arg);
        }
        if (arg > sq2p1) {
            return HALF_PI - mxatan(1 / arg);
        }
        return HALF_PI / 2 + mxatan((arg - 1) / (arg + 1));
    }
}
