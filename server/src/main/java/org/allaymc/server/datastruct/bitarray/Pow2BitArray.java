package org.allaymc.server.datastruct.bitarray;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.cloudburstmc.math.GenericMath;

import java.util.Arrays;

/**
 * @author JukeboxMC | daoge_cmd
 */
public record Pow2BitArray(BitArrayVersion version, int size, int[] words) implements BitArray {

    public Pow2BitArray(BitArrayVersion version, int size, int[] words) {
        this.size = size;
        this.version = version;
        this.words = words;

        var expectedWordsLength = GenericMath.ceil((float) size / version.entriesPerWord);
        if (words.length != expectedWordsLength) {
            throw new IllegalArgumentException("Invalid length given for storage, got: " + words.length +
                                               " but expected: " + expectedWordsLength);
        }
    }

    public void set(int index, int value) {
        Preconditions.checkElementIndex(index, this.size);
        if (value < 0 || value > this.version.maxEntryIndex) {
            throw new IllegalArgumentException(String.format("Max value: %s. Received value %s", this.version.maxEntryIndex, value));
        }

        var bitIndex = index * this.version.bits;
        var arrayIndex = bitIndex >> 5;
        var offset = bitIndex & 31;
        this.words[arrayIndex] = this.words[arrayIndex] & ~(this.version.maxEntryIndex << offset) | (value & this.version.maxEntryIndex) << offset;
    }

    public int get(int index) {
        Preconditions.checkElementIndex(index, this.size);
        var bitIndex = index * this.version.bits;
        var arrayIndex = bitIndex >> 5;
        var wordOffset = bitIndex & 31;
        return this.words[arrayIndex] >>> wordOffset & this.version.maxEntryIndex;
    }

    @Override
    public BitArray copy() {
        return new Pow2BitArray(this.version, this.size, Arrays.copyOf(this.words, this.words.length));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pow2BitArray that)) return false;
        return size == that.size && version == that.version && Arrays.equals(words, that.words);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(version, size, Arrays.hashCode(words));
    }
}