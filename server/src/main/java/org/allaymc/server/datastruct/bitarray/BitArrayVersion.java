package org.allaymc.server.datastruct.bitarray;

import org.cloudburstmc.math.GenericMath;

/**
 * {@code BitArrayVersion} identifies the supported palette-index widths and the layout of
 * entries within thirty-two-bit words. Each constant defines an unsigned maximum, a word
 * capacity, and the next larger representation used when a palette outgrows its current
 * index range. The zero-bit version uses the shared singleton instead of allocating words.
 *
 * <p>
 * Widths of three, five, and six bits leave padding at the high end of each word, so their
 * factories choose whole-slot addressing. Other nonzero widths divide the word evenly and
 * use contiguous bit offsets. Header lookup also recognizes the copy-last sentinel
 * {@code 0x7F} as a {@code null} version when reading, allowing palette persistence to
 * reuse an earlier palette.
 * @author JukeboxMC | daoge_cmd
 */
public enum BitArrayVersion {

    /**
     * 16-bit entries with 2 slots per packed word.
     */
    V16(16, 2, null),
    /**
     * 8-bit entries with 4 slots per packed word.
     */
    V8(8, 4, V16),
    /**
     * 6-bit entries with 5 slots per packed word.
     */
    V6(6, 5, V8),
    /**
     * 5-bit entries with 6 slots per packed word.
     */
    V5(5, 6, V6),
    /**
     * 4-bit entries with 8 slots per packed word.
     */
    V4(4, 8, V5),
    /**
     * 3-bit entries with 10 slots per packed word.
     */
    V3(3, 10, V4),
    /**
     * 2-bit entries with 16 slots per packed word.
     */
    V2(2, 16, V3),
    /**
     * 1-bit entries with 32 slots per packed word.
     */
    V1(1, 32, V2),
    /**
     * Zero-bit representation of uniform zero palette indices.
     */
    V0(0, 0, V1);

    /**
     * {@code VALUES} caches the declaration order for width selection. The order runs from
     * the largest representation down to the zero-bit singleton.
     */
    private static final BitArrayVersion[] VALUES = values();

    /**
     * Number of bits in each unsigned entry.
     */
    public final byte bits;
    /**
     * Number of complete entry slots per word.
     */
    public final byte entriesPerWord;
    /**
     * Largest unsigned value representable by this version.
     */
    public final int maxEntryIndex;
    /**
     * {@code next} identifies the larger layout used after palette growth. The largest
     * layout has {@code null}, so callers must check before resizing.
     */
    public final BitArrayVersion next;

    /**
     * Creates a version with its width, word capacity, and growth successor. The maximum
     * entry value is derived from the width; {@code next} is {@code null} for the largest
     * representation.
     */
    BitArrayVersion(int bits, int entriesPerWord, BitArrayVersion next) {
        this.bits = (byte) bits;
        this.entriesPerWord = (byte) entriesPerWord;
        this.maxEntryIndex = (1 << this.bits) - 1;
        this.next = next;
    }

    /**
     * Returns the smallest version that represents {@code maxEntryIndex}. Values at or
     * below zero choose {@code V0}; callers must stay within the sixteen-bit maximum
     * because the growth chain ends there.
     */
    public static BitArrayVersion getMinimalVersion(int maxEntryIndex) {
        var version = V0;
        while (version.maxEntryIndex < maxEntryIndex) {
            version = version.next;
        }
        return version;
    }

    /**
     * Selects a version using either header width or word-capacity comparison. Reading
     * matches {@code bits} exactly and returns {@code null} for {@code 0x7F}; unmatched
     * values throw {@code IllegalArgumentException}. Non-reading lookup returns the first
     * declared version whose word capacity does not exceed the supplied value.
     */
    public static BitArrayVersion get(int version, boolean read) {
        for (BitArrayVersion ver : VALUES) {
            if ((!read && ver.entriesPerWord <= version) || (read && ver.bits == version)) {
                return ver;
            }
        }

        // Copy-last palette headers carry no independent layout, so reading their reserved
        // width returns null for the palette decoder to handle.
        if (version == 0x7F && read) return null;
        throw new IllegalArgumentException("Invalid palette version: " + version);
    }

    /**
     * Returns the smallest supported width at least as large as {@code bits}. Nonpositive
     * requests select {@code V0}, while requests above sixteen return {@code null} rather
     * than throwing an exception.
     */
    public static BitArrayVersion forBitsCeil(int bits) {
        for (int i = VALUES.length - 1; i >= 0; i--) {
            var version = VALUES[i];
            if (version.bits >= bits) return version;
        }

        return null;
    }

    /**
     * Creates zero-initialized index storage using this version's word layout. {@code V0}
     * returns the shared singleton regardless of {@code size}; other versions allocate the
     * computed word count, subject to {@code getWordsForSize} floating-point sizing
     * limits.
     */
    public BitArray createArray(int size) {
        if (this == V0) {
            return SingletonBitArray.INSTANCE;
        }
        return this.createArray(size, new int[getWordsForSize(size)]);
    }

    /**
     * Returns the word count computed for {@code size} logical entries. {@code V0}
     * requires no words. Other versions round up a {@code float} quotient; converting
     * large sizes to {@code float} can lose precision and underestimate the required
     * storage.
     */
    public int getWordsForSize(int size) {
        if (this == V0) {
            return 0;
        }
        // Round the float quotient upward; large sizes can lose entry precision before this rounding.
        return GenericMath.ceil((float) size / this.entriesPerWord);
    }

    /**
     * Creates index storage retaining the supplied {@code words} array without copying it.
     * {@code V0} returns the shared singleton regardless of {@code size} or {@code words};
     * nonzero layouts validate the retained word count using their floating-point sizing
     * calculation.
     */
    public BitArray createArray(int size, int[] words) {
        if (this == V0) {
            return SingletonBitArray.INSTANCE;
        } else if (this == V3 || this == V5 || this == V6) {
            // Whole-slot addressing skips the unused high bits in these versions, preventing
            // indices from being assigned across word boundaries.
            return new PaddedBitArray(this, size, words);
        } else {
            return new Pow2BitArray(this, size, words);
        }
    }
}
