package org.allaymc.server.datastruct.bitarray;

import com.google.common.base.Preconditions;
import org.cloudburstmc.math.GenericMath;

/**
 * @author JukeboxMC | daoge_cmd
 */
public enum BitArrayVersion {

    V16(16, 2, null),
    V8(8, 4, V16),
    V6(6, 5, V8), // 2 bit padding
    V5(5, 6, V6), // 2 bit padding
    V4(4, 8, V5),
    V3(3, 10, V4), // 2 bit padding
    V2(2, 16, V3),
    V1(1, 32, V2),
    V0(0, 0, V1);

    private static final BitArrayVersion[] VALUES = values();

    public final byte bits;
    public final byte entriesPerWord;
    public final int maxEntryIndex;
    public final BitArrayVersion next;

    BitArrayVersion(int bits, int entriesPerWord, BitArrayVersion next) {
        this.bits = (byte) bits;
        this.entriesPerWord = (byte) entriesPerWord;
        this.maxEntryIndex = (1 << this.bits) - 1;
        this.next = next;
    }

    public static BitArrayVersion getMinimalVersion(int maxEntryIndex) {
        var version = V0;
        while (version.maxEntryIndex < maxEntryIndex) {
            version = version.next;
        }
        return version;
    }

    public static BitArrayVersion get(int version, boolean read) {
        for (BitArrayVersion ver : VALUES) {
            if ((!read && ver.entriesPerWord <= version) || (read && ver.bits == version)) {
                return ver;
            }
        }

        if (version == 0x7F && read) return null;
        throw new IllegalArgumentException("Invalid palette version: " + version);
    }

    public static BitArrayVersion forBitsCeil(int bits) {
        for (int i = VALUES.length - 1; i >= 0; i--) {
            var version = VALUES[i];
            if (version.bits >= bits) return version;
        }

        return null;
    }

    public BitArray createArray(int size) {
        if (this == V0) {
            return SingletonBitArray.INSTANCE;
        }
        return this.createArray(size, new int[getWordsForSize(size)]);
    }

    public int getWordsForSize(int size) {
        Preconditions.checkArgument(this != V0);
        return GenericMath.ceil((float) size / this.entriesPerWord);
    }

    public BitArray createArray(int size, int[] words) {
        if (this == V0) {
            return SingletonBitArray.INSTANCE;
        } else if (this == V3 || this == V5 || this == V6) {
            // Padded palettes aren't able to use bitwise operations due to their padding.
            return new PaddedBitArray(this, size, words);
        } else {
            return new Pow2BitArray(this, size, words);
        }
    }
}
