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

import cn.allay.api.math.vector.*;
import org.cloudburstmc.math.TrigMath;

import java.awt.*;

/**
 * Class containing generic mathematical functions.
 */
public final class GenericMath {

    /**
     * A "close to zero" double epsilon value for use
     */
    public static final double DBL_EPSILON = Double.longBitsToDouble(0x3cb0000000000000L);
    /**
     * A "close to zero" float epsilon value for use
     */
    public static final float FLT_EPSILON = Float.intBitsToFloat(0x34000000);

    private GenericMath() {
    }

    /**
     * Gets the difference between two angles This value is always positive (0 - 180)
     *
     * @param angle1 The first angle
     * @param angle2 The second angle
     * @return the positive angle difference
     */
    public static float degreeDifference(final float angle1, final float angle2) {
        return Math.abs(GenericMath.wrapAngleDeg(angle1 - angle2));
    }

    /**
     * Gets the difference between two radians This value is always positive (0 - PI)
     *
     * @param radian1 The first angle
     * @param radian2 The second angle
     * @return the positive radian difference
     */
    public static double radianDifference(final double radian1, final double radian2) {
        return Math.abs(GenericMath.wrapAngleRad(radian1 - radian2));
    }

    /**
     * Wraps the angle between -180 and 180 degrees
     *
     * @param angle to wrap
     * @return -180 &lt; angle &lt;= 180
     */
    public static float wrapAngleDeg(float angle) {
        angle %= 360f;
        if (angle <= -180) {
            return angle + 360;
        } else if (angle > 180) {
            return angle - 360;
        } else {
            return angle;
        }
    }

    /**
     * Wraps the radian between -PI and PI
     *
     * @param angle to wrap
     * @return -PI &lt; radian &lt;= PI
     */
    public static double wrapAngleRad(double angle) {
        angle %= TrigMath.TWO_PI;
        if (angle <= -TrigMath.PI) {
            return angle + TrigMath.TWO_PI;
        }
        if (angle > TrigMath.PI) {
            return angle - TrigMath.TWO_PI;
        }
        return angle;
    }

    /**
     * Wraps the pitch angle between -90 and 90 degrees
     *
     * @param angle to wrap
     * @return -90 &lt; angle &lt; 90
     */
    public static float wrapAnglePitchDeg(float angle) {
        angle = GenericMath.wrapAngleDeg(angle);
        if (angle < -90) {
            return -90;
        }
        if (angle > 90) {
            return 90;
        }
        return angle;
    }

    /**
     * Wraps a byte between 0 and 256
     *
     * @param value to wrap
     * @return 0 &lt; byte &lt; 256
     */
    public static byte wrapByte(int value) {
        value %= 256;
        if (value < 0) {
            value += 256;
        }
        return (byte) value;
    }

    /**
     * Rounds a number to the amount of decimals specified
     *
     * @param input    to round
     * @param decimals to round to
     * @return the rounded number
     */
    public static double round(final double input, final int decimals) {
        final double p = Math.pow(10, decimals);
        return Math.round(input * p) / p;
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated value
     */
    public static double lerp(final double a, final double b, final double percent) {
        return (1 - percent) * a + percent * b;
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated value
     */
    public static float lerp(final float a, final float b, final float percent) {
        return (1 - percent) * a + percent * b;
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated value
     */
    public static int lerp(final int a, final int b, final int percent) {
        return (1 - percent) * a + percent * b;
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated vector
     */
    public static Vec3f lerp(final Vec3f a, final Vec3f b, final float percent) {
        return a.mul(1 - percent).add(b.mul(percent));
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated vector
     */
    public static Vec3d lerp(final Vec3d a, final Vec3d b, final double percent) {
        return a.mul(1 - percent).add(b.mul(percent));
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated vector
     */
    public static Vec2f lerp(final Vec2f a, final Vec2f b, final float percent) {
        return a.mul(1 - percent).add(b.mul(percent));
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return the interpolated vector
     */
    public static Vec2d lerp(final Vec2d a, final Vec2d b, final double percent) {
        return a.mul(1 - percent).add(b.mul(percent));
    }

    /**
     * Calculates the value at x using linear interpolation
     *
     * @param x  the X coord of the value to interpolate
     * @param x1 the X coord of q0
     * @param x2 the X coord of q1
     * @param q0 the first known value (x1)
     * @param q1 the second known value (x2)
     * @return the interpolated value
     */
    public static double lerp(final double x, final double x1, final double x2, final double q0, final double q1) {
        return ((x2 - x) / (x2 - x1)) * q0 + ((x - x1) / (x2 - x1)) * q1;
    }

    /**
     * Calculates the linear interpolation between a and b with the given percent
     *
     * @param a       The first know value
     * @param b       The second know value
     * @param percent The percent
     * @return Color
     */
    public static Color lerp(final Color a, final Color b, final float percent) {
        final int red = (int) GenericMath.lerp(a.getRed(), b.getRed(), percent);
        final int blue = (int) GenericMath.lerp(a.getBlue(), b.getBlue(), percent);
        final int green = (int) GenericMath.lerp(a.getGreen(), b.getGreen(), percent);
        final int alpha = (int) GenericMath.lerp(a.getAlpha(), b.getAlpha(), percent);
        return new Color(red, green, blue, alpha);
    }


    /**
     * Calculates the value at x,y using bilinear interpolation
     *
     * @param x   the X coord of the value to interpolate
     * @param y   the Y coord of the value to interpolate
     * @param q00 the first known value (x1, y1)
     * @param q01 the second known value (x1, y2)
     * @param q10 the third known value (x2, y1)
     * @param q11 the fourth known value (x2, y2)
     * @param x1  the X coord of q00 and q01
     * @param x2  the X coord of q10 and q11
     * @param y1  the Y coord of q00 and q10
     * @param y2  the Y coord of q01 and q11
     * @return the interpolated value
     */
    public static double biLerp(final double x, final double y, final double q00, final double q01,
                                final double q10, final double q11, final double x1, final double x2, final double y1, final double y2) {
        final double q0 = GenericMath.lerp(x, x1, x2, q00, q10);
        final double q1 = GenericMath.lerp(x, x1, x2, q01, q11);
        return GenericMath.lerp(y, y1, y2, q0, q1);
    }

    /**
     * Calculates the value at x,y,z using trilinear interpolation
     *
     * @param x    the X coord of the value to interpolate
     * @param y    the Y coord of the value to interpolate
     * @param z    the Z coord of the value to interpolate
     * @param q000 the first known value (x1, y1, z1)
     * @param q001 the second known value (x1, y2, z1)
     * @param q010 the third known value (x1, y1, z2)
     * @param q011 the fourth known value (x1, y2, z2)
     * @param q100 the fifth known value (x2, y1, z1)
     * @param q101 the sixth known value (x2, y2, z1)
     * @param q110 the seventh known value (x2, y1, z2)
     * @param q111 the eighth known value (x2, y2, z2)
     * @param x1   the X coord of q000, q001, q010 and q011
     * @param x2   the X coord of q100, q101, q110 and q111
     * @param y1   the Y coord of q000, q010, q100 and q110
     * @param y2   the Y coord of q001, q011, q101 and q111
     * @param z1   the Z coord of q000, q001, q100 and q101
     * @param z2   the Z coord of q010, q011, q110 and q111
     * @return the interpolated value
     */
    public static double triLerp(final double x, final double y, final double z, final double q000, final double q001,
                                 final double q010, final double q011, final double q100, final double q101, final double q110, final double q111,
                                 final double x1, final double x2, final double y1, final double y2, final double z1, final double z2) {
        final double q00 = GenericMath.lerp(x, x1, x2, q000, q100);
        final double q01 = GenericMath.lerp(x, x1, x2, q010, q110);
        final double q10 = GenericMath.lerp(x, x1, x2, q001, q101);
        final double q11 = GenericMath.lerp(x, x1, x2, q011, q111);
        final double q0 = GenericMath.lerp(y, y1, y2, q00, q10);
        final double q1 = GenericMath.lerp(y, y1, y2, q01, q11);
        return GenericMath.lerp(z, z1, z2, q0, q1);
    }

    /**
     * Blends two colors into one.
     *
     * @param a The first color
     * @param b The second color
     * @return The blended color
     */
    public static Color blend(final Color a, final Color b) {
        return GenericMath.lerp(a, b, a.getAlpha() / 255f);
    }

    /**
     * Clamps the value between the low and high boundaries
     *
     * @param value The value to clamp
     * @param low   The low bound of the clamp
     * @param high  The high bound of the clamp
     * @return the clamped value
     */
    public static double clamp(final double value, final double low, final double high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }

    /**
     * Clamps the value between the low and high boundaries
     *
     * @param value The value to clamp
     * @param low   The low bound of the clamp
     * @param high  The high bound of the clamp
     * @return the clamped value
     */
    public static int clamp(final int value, final int low, final int high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }

    /**
     * Returns a fast estimate of the inverse square root of the value
     *
     * @param a The value
     * @return The estimate of the inverse square root
     */
    public static double inverseSqrt(double a) {
        final double halfA = 0.5d * a;
        a = Double.longBitsToDouble(0x5FE6EB50C7B537AAL - (Double.doubleToRawLongBits(a) >> 1));
        return a * (1.5d - halfA * a * a);
    }

    /**
     * Returns a fast estimate of the square root of the value
     *
     * @param a The value
     * @return The estimate of the square root
     */
    public static double sqrt(final double a) {
        return a * GenericMath.inverseSqrt(a);
    }

    /**
     * Rounds 'a' down to the closest integer
     *
     * @param a The value to floor
     * @return The closest integer
     */
    public static int floor(final double a) {
        final int y = (int) a;
        if (a < y) {
            return y - 1;
        }
        return y;
    }

    /**
     * Rounds 'a' down to the closest integer
     *
     * @param a The value to floor
     * @return The closest integer
     */
    public static int floor(final float a) {
        final int y = (int) a;
        if (a < y) {
            return y - 1;
        }
        return y;
    }

    /**
     * Rounds 'a' down to the closest long
     *
     * @param a The value to floor
     * @return The closest long
     */
    public static long floorl(final double a) {
        final long y = (long) a;
        if (a < y) {
            return y - 1;
        }
        return y;
    }

    /**
     * Rounds 'a' down to the closest long
     *
     * @param a The value to floor
     * @return The closest long
     */
    public static long floorl(final float a) {
        final long y = (long) a;
        if (a < y) {
            return y - 1;
        }
        return y;
    }

    /**
     * Gets the maximum byte value from two values
     *
     * @param value1 The first value
     * @param value2 The second value
     * @return the maximum of value1 and value2
     */
    public static byte max(final byte value1, final byte value2) {
        return value1 > value2 ? value1 : value2;
    }

    /**
     * Rounds an integer up to the next power of 2.
     *
     * @param a The integer to round
     * @return the lowest power of 2 greater or equal to 'a'
     */
    public static int roundUpPow2(int a) {
        if (a <= 0) {
            return 1;
        } else if (a > 0x40000000) {
            throw new IllegalArgumentException("Rounding " + a + " to the next highest power of two would exceed the int range");
        } else {
            a--;
            a |= a >> 1;
            a |= a >> 2;
            a |= a >> 4;
            a |= a >> 8;
            a |= a >> 16;
            a++;
            return a;
        }
    }

    /**
     * Rounds an integer up to the next power of 2.
     *
     * @param a The long to round
     * @return the lowest power of 2 greater or equal to 'a'
     */
    public static long roundUpPow2(long a) {
        if (a <= 0) {
            return 1;
        } else if (a > 0x4000000000000000L) {
            throw new IllegalArgumentException("Rounding " + a + " to the next highest power of two would exceed the int range");
        } else {
            a--;
            a |= a >> 1;
            a |= a >> 2;
            a |= a >> 4;
            a |= a >> 8;
            a |= a >> 16;
            a |= a >> 32;
            a++;
            return a;
        }
    }

    /**
     * Casts a value to a float. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a float
     */
    public static Float castFloat(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).floatValue();
        }
        try {
            return Float.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to a byte. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a byte
     */
    public static Byte castByte(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).byteValue();
        }
        try {
            return Byte.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to a short. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a short
     */
    public static Short castShort(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).shortValue();
        }
        try {
            return Short.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to an integer. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as an int
     */
    public static Integer castInt(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).intValue();
        }
        try {
            return Integer.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to a double. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a double
     */
    public static Double castDouble(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        try {
            return Double.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to a long. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a long
     */
    public static Long castLong(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            return ((Number) o).longValue();
        }
        try {
            return Long.valueOf(o.toString());
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Casts a value to a boolean. May return null.
     *
     * @param o The object to attempt to cast
     * @return The object as a boolean
     */
    public static Boolean castBoolean(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o instanceof String) {
            try {
                return Boolean.parseBoolean((String) o);
            } catch (final IllegalArgumentException ex) {
                return null;
            }
        }

        return null;
    }

    /**
     * Calculates the mean of a set of values
     *
     * @param values to calculate the mean of
     * @return the mean of the values
     */
    public static int mean(final int... values) {
        int sum = 0;
        for (final int v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    /**
     * Calculates the mean of a set of values.
     *
     * @param values to calculate the mean of
     * @return the mean of the values
     */
    public static double mean(final double... values) {
        double sum = 0;
        for (final double v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    /**
     * Converts an integer to hexadecimal form with at least the minimum of digits specified (by adding leading zeros).
     *
     * @param dec       The integer to convert
     * @param minDigits The minimum of digits in the hexadecimal form
     * @return The integer in hexadecimal form
     */
    public static String decToHex(final int dec, final int minDigits) {
        String ret = Integer.toHexString(dec);
        while (ret.length() < minDigits) {
            ret = '0' + ret;
        }
        return ret;
    }

    /**
     * Returns the modulo of 'a' by 'div' with corrections for negative numbers.
     *
     * @param a   The number as an int
     * @param div The div as an int
     * @return The corrected modulo
     */
    public static int mod(final int a, final int div) {
        final int remainder = a % div;
        return remainder < 0 ? remainder + div : remainder;
    }

    /**
     * Returns the modulo of 'a' by 'div' with corrections for negative numbers.
     *
     * @param a   The dividend
     * @param div The divider
     * @return The corrected modulo
     */
    public static float mod(final float a, final float div) {
        final float remainder = a % div;
        return remainder < 0 ? remainder + div : remainder;
    }

    /**
     * Returns the modulo of 'a' by 'div' with corrections for negative numbers.
     *
     * @param a   The dividend
     * @param div The divider
     * @return The corrected modulo
     */
    public static double mod(final double a, final double div) {
        final double remainder = a % div;
        return remainder < 0 ? remainder + div : remainder;
    }

    /**
     * Determines if the given number is a power of two. A number is a power of 2 if it is 1 or greater, and it contains no similar bits of the given number - 1.
     *
     * @param num the number to check
     * @return true if num is a power of two
     */
    public static boolean isPowerOfTwo(final int num) {
        return num > 0 && ((num & (num - 1)) == 0);
    }

    /**
     * Converts a multiplication into a shift.
     *
     * @param a the multiplicand
     * @return the left shift required to multiply by the multiplicand
     */
    public static int multiplyToShift(final int a) {
        if (a < 1) {
            throw new IllegalArgumentException("Multiplicand must be at least 1");
        }
        final int shift = 31 - Integer.numberOfLeadingZeros(a);
        if ((1 << shift) != a) {
            throw new IllegalArgumentException("Multiplicand must be a power of 2");
        }
        return shift;
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec2f normalizeSafe(final Vec2f v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec2f.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec2d normalizeSafe(final Vec2d v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec2d.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec3f normalizeSafe(final Vec3f v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec3f.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec3d normalizeSafe(final Vec3d v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec3d.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec4f normalizeSafe(final Vec4f v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec4f.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static Vec4d normalizeSafe(final Vec4d v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return Vec4d.ZERO;
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static VecNf normalizeSafe(final VecNf v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return new VecNf(v.axisCount());
        }
    }

    /**
     * Attempts to normalize a vector. If this fails, the method catches the exception and return a zero vector of the same dimension instead.
     *
     * @param v The vector to attempt to normalize
     * @return The normalized vector, or the zero vector if it couldn't be normalized.
     */
    public static VecNd normalizeSafe(final VecNd v) {
        try {
            return v.normalize();
        } catch (final ArithmeticException ex) {
            return new VecNd(v.axisCount());
        }
    }

}
