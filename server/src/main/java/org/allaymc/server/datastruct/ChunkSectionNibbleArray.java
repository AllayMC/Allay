package org.allaymc.server.datastruct;

import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Arrays;

/**
 * {@code ChunkSectionNibbleArray} stores four-bit values for a
 * sixteen-by-sixteen-by-sixteen chunk section, commonly used for light levels. The logical
 * index increases along {@code x}, then {@code z}, then {@code y}; adjacent indices occupy
 * the low and high nibbles of one byte. Coordinates must lie from zero through fifteen,
 * and values must remain in that same unsigned range.
 *
 * <p>
 * The tracked {@code checkSum} selects implicit all-zero or all-fifteen states, allowing
 * {@code bytes} to be absent for uniform sections. Mixed writes allocate storage and
 * update the checksum before deciding whether explicit bytes remain necessary. A supplied
 * byte array is retained, but its existing contents do not initialize the checksum;
 * callers must therefore not assume that constructor input is immediately reflected by
 * reads. The backing-array accessor exposes storage directly and bypasses checksum
 * tracking when modified.
 */
public final class ChunkSectionNibbleArray {
    /**
     * {@code CHECK_SUM_MAX} is the sum of all section entries at light level fifteen.
     * Reads use that checksum to select the implicit full-light state.
     */
    public static final int CHECK_SUM_MAX = 61440;
    /**
     * {@code BYTES_LENGTH} is the byte count needed for two nibbles per byte across all
     * four thousand ninety-six section positions.
     */
    public static final int BYTES_LENGTH = 2048;
    /**
     * {@code bytes} holds explicit mixed-section storage or {@code null} for an implicit
     * uniform state. Supplied arrays and the testing accessor share this reference without
     * copying.
     */
    private byte[] bytes;
    /**
     * {@code checkSum} selects uniform read shortcuts at zero and {@code CHECK_SUM_MAX}.
     * Writes adjust this counter using stored nibbles; constructor-provided bytes do not
     * initialize it.
     */
    private int checkSum = 0;

    /**
     * Creates a section initially represented by an implicit all-zero state.
     */
    public ChunkSectionNibbleArray() {
        this(null);
    }

    /**
     * Creates a section retaining the supplied array, or implicit zero storage for
     * {@code null}. A non-null array must contain exactly {@code BYTES_LENGTH} bytes; its
     * contents do not initialize {@code checkSum}.
     */
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
     * Returns the section index with {@code x} changing fastest, then {@code z}, then
     * {@code y}. Each coordinate must be from zero through fifteen; this method packs bits
     * without validating or masking inputs.
     */
    public static int calculateIndex(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        // Put X in the low bits to preserve the section layout consumed by coordinate-based
        // light reads and writes.
        return y << 8 | z << 4 | x;
    }

    /**
     * Returns index parity, selecting the low nibble for even indices.
     */
    private static int occupiesSmallerBits(int i) {
        return i & 1;
    }

    /**
     * Returns the backing-byte index shared by each adjacent nibble pair.
     */
    private static int getArrayIndex(int i) {
        return i >> 1;
    }

    /**
     * Returns the light value for the selected section position. All three coordinates
     * must lie from zero through fifteen and are packed in {@code x}, {@code z}, {@code y}
     * order without runtime validation.
     */
    public int get(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        return this.get(calculateIndex(x, y, z));
    }

    /**
     * Stores a light value at the supplied section coordinates. Coordinates and
     * {@code value} must lie from zero through fifteen; this overload uses the same
     * checksum-tracked storage update as the linear-index setter.
     */
    public void set(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int value) {
        this.set(calculateIndex(x, y, z), value);
    }

    /**
     * Returns the current backing storage directly, including {@code null} for implicit
     * states. The returned array is shared, so direct writes bypass checksum tracking and
     * can make later logical reads inconsistent.
     */
    @VisibleForTesting
    public byte[] getByteArray() {
        return bytes;
    }

    /**
     * Returns hexadecimal light values grouped by section rows and layers. Newlines
     * separate each sixteen-entry row, and an extra newline separates each
     * two-hundred-fifty-six-entry layer in the linear section order.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // Rows follow X and layers follow Y, matching the section index ordering used by the
        // coordinate accessors.
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

    /**
     * Returns the light value for the selected section position. The logical index must
     * lie from zero through {@code 4095}; implicit checksum states return their uniform
     * value without consulting storage.
     */
    @Range(from = 0, to = 15)
    public int get(@Range(from = 0, to = 4096) int index) {
        // Uniform checksum states bypass byte storage, including the initial state before any
        // constructor-provided bytes have been tracked.
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

    /**
     * Stores one light value and updates the tracked section checksum. The index must lie
     * from zero through {@code 4095}, and {@code value} must lie from zero through
     * fifteen; the implementation does not validate these ranges.
     *
     * <p>
     * Uniform states can omit {@code bytes}, so a write first materializes storage when
     * the requested value differs. The checksum changes by the difference between the old
     * stored nibble and supplied value. A checksum at either uniform extreme discards
     * explicit storage; other checksums retain the modified byte. Out-of-range values can
     * therefore corrupt the checksum even though the stored nibble is masked.
     */
    public void set(@Range(from = 0, to = 4096) int index, @Range(from = 0, to = 15) int value) {
        // Materialize an implicit section before changing one entry, because mixed values
        // require separately addressable nibbles for later reads.
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
        // Mask the replacement nibble so it cannot overwrite the adjacent entry sharing the
        // same backing byte.
        int l = (value & 15) << 4 * j;
        int oldValue = bytes[i] >> 4 * j & 15;
        if (value == oldValue) {
            return;
        }
        // Decide uniform-state compaction from the updated checksum before retaining bytes, so
        // reads can use the checksum as their representation selector.
        checkSum = checkSum - oldValue + value;
        if (checkSum == 0 || checkSum == CHECK_SUM_MAX) {
            bytes = null;
        } else {
            bytes[i] = (byte) (bytes[i] & k | l);
        }
    }

    /**
     * Resets every logical value to zero and releases explicit byte storage.
     */
    public void reset() {
        checkSum = 0;
        bytes = null;
    }
}
