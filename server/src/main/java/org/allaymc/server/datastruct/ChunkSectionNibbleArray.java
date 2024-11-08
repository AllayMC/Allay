package org.allaymc.server.datastruct;

import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Arrays;

/**
 * A chunk section nibble array is an array of nibbles for each block position in
 * a chunk section (a 16x16x16 area). It is most often used to store light data.
 * <p>
 * A {@index nibble} is 4 bits, storing an integer from {@code 0} to
 * {@code 15}. It takes half the space of a byte.
 * <p>
 * The nibbles are stored in an X-Z-Y major order; in the backing array,
 * the indices increases by first increasing X, then Z, and finally Y.
 */
public final class ChunkSectionNibbleArray {
    public static final int CHECK_SUM_MAX = 61440;
    public static final int BYTES_LENGTH = 2048;
    private byte[] bytes;
    private int checkSum = 0;

    public static int calculateIndex(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        return y << 8 | z << 4 | x;
    }

    public ChunkSectionNibbleArray() {
        this(null);
    }

    public ChunkSectionNibbleArray(byte[] bytes) {
        if (bytes == null) {
            return;
        }
        this.bytes = bytes;
        if (bytes.length != BYTES_LENGTH) {
            throw new IllegalArgumentException("Provided byte array should be " + BYTES_LENGTH + " long instead of " + bytes.length);
        }
    }

    /**
     * @return the integer value of a nibble, in {@code [0, 15]}
     */
    public int get(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        return this.get(calculateIndex(x, y, z));
    }

    /**
     * Sets the value of a nibble.
     * <p>
     * If the {@code value} has bits outside the lowest 4 set to {@code 1},
     * (value is outside of {@code [0, 15]}), the extraneous bits are discarded.
     */
    public void set(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int value) {
        this.set(calculateIndex(x, y, z), value);
    }

    @VisibleForTesting
    public byte[] getByteArray() {
        return bytes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4096; i++) {
            stringBuilder.append(Integer.toHexString(this.get(i)));
            if ((i & 15) == 15) {
                stringBuilder.append("\n");
            }

            if ((i & 0xFF) == 255) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    @Range(from = 0, to = 15)
    public int get(@Range(from = 0, to = 4096) int index) {
        if (checkSum == 0) {
            return 0;
        } else if (checkSum == CHECK_SUM_MAX) {
            return 15;
        } else {
            int i = getArrayIndex(index);
            int j = occupiesSmallerBits(index);
            return this.bytes[i] >> 4 * j & 15;
        }
    }

    public void set(@Range(from = 0, to = 4096) int index, @Range(from = 0, to = 15) int value) {
        if (bytes == null) {
            if ((value == 0 && checkSum == 0) || (value == 15 && checkSum == CHECK_SUM_MAX)) {
                return;
            }
            bytes = new byte[BYTES_LENGTH];
            if (checkSum == CHECK_SUM_MAX) {
                Arrays.fill(bytes, (byte) 15);
            }
        }
        int i = getArrayIndex(index);
        int j = occupiesSmallerBits(index);
        int k = ~(15 << 4 * j);
        int l = (value & 15) << 4 * j;
        int oldValue = bytes[i] >> 4 * j & 15;
        if (value == oldValue) {
            return;
        }
        checkSum = checkSum - oldValue + value;
        if (checkSum == 0 || checkSum == CHECK_SUM_MAX) {
            bytes = null;
        } else {
            bytes[i] = (byte) (bytes[i] & k | l);
        }
    }

    /**
     * {@return if the nibble at {@code n} is stored in the less
     * significant (smaller) 4 bits of the byte in the backing array}
     */
    private static int occupiesSmallerBits(int i) {
        return i & 1;
    }

    private static int getArrayIndex(int i) {
        return i >> 1;
    }
}
