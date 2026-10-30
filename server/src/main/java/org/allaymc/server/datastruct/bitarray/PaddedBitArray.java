package org.allaymc.server.datastruct.bitarray;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.cloudburstmc.math.GenericMath;

import java.util.Arrays;

/**
 * {@code PaddedBitArray} stores fixed-width unsigned palette indices in mutable integer
 * words. Entries occupy complete slots within each word, leaving unused high bits when
 * their width does not divide thirty-two. The version supplies both slot width and entries
 * per word, so no entry crosses a word boundary.
 *
 * <p>
 * Writes clear only the selected slot before combining the new value with the remaining
 * bits, preserving adjacent palette indices. The constructor retains the supplied
 * {@code words} array, and the record accessor exposes that same array; callers must
 * coordinate all mutations and preserve the chosen layout. {@code copy()} duplicates the
 * backing words so edits through one copy do not alter the other.
 * @param version packing width and slot layout
 * @param size logical entry count
 * @param words the supplied {@code words} array remains shared; external writes change
 *              the logical values read through this record.
 * @author JukeboxMC | daoge_cmd
 */
public record PaddedBitArray(BitArrayVersion version, int size, int[] words) implements BitArray {

    /**
     * Creates a packed array over the supplied {@code words} without copying them. Its
     * length must equal the rounded-up {@code float} quotient of {@code size} and
     * {@code version.entriesPerWord}; incompatible storage throws
     * {@code IllegalArgumentException}. Converting large sizes to {@code float} can lose
     * precision and admit fewer words than the mathematical entry count requires. Callers
     * must select a nonzero version with complete entry slots per word; this constructor
     * checks storage length rather than version suitability.
     */
    public PaddedBitArray(BitArrayVersion version, int size, int[] words) {
        this.size = size;
        this.version = version;
        this.words = words;

        // Match BitArrayVersion's float-based sizing, including possible precision loss at large logical sizes.
        var expectedWordsLength = GenericMath.ceil((float) size / version.entriesPerWord);
        if (words.length != expectedWordsLength) {
            throw new IllegalArgumentException("Invalid length given for storage, got: " + words.length +
                                               " but expected: " + expectedWordsLength);
        }
    }

    /**
     * Replaces one logical palette index while retaining all other packed entries. The
     * index must lie from zero inclusive to {@code size} exclusive, and {@code value} must
     * fit {@code version.maxEntryIndex}; violations throw an index exception or
     * {@code IllegalArgumentException}.
     *
     * <p>
     * The chosen layout determines the containing word and slot shift. A mask clears that
     * slot before the shifted new value is combined with the original word, so writing one
     * palette entry does not overwrite its neighbors. Callers must coordinate concurrent
     * changes to the shared backing array.
     */
    @Override
    public void set(int index, int value) {
        Preconditions.checkElementIndex(index, this.size);
        if (value < 0 || value > this.version.maxEntryIndex) {
            throw new IllegalArgumentException(String.format("Max value: %s. Received value %s", this.version.maxEntryIndex, value));
        }

        // Whole-slot addressing skips unused high bits instead of allowing the next entry to
        // straddle a word boundary.
        var arrayIndex = index / this.version.entriesPerWord;
        var offset = (index % this.version.entriesPerWord) * this.version.bits;
        // Preserve neighboring slots when replacing this entry, because every word contains
        // several independently addressable palette indices.
        this.words[arrayIndex] = this.words[arrayIndex] & ~(this.version.maxEntryIndex << offset) | (value & this.version.maxEntryIndex) << offset;
    }

    /**
     * Returns the unsigned value stored in the selected logical slot. The index must lie
     * from zero inclusive to {@code size} exclusive; extraction shifts the containing word
     * and masks off neighboring entries.
     */
    @Override
    public int get(int index) {
        Preconditions.checkElementIndex(index, this.size);
        // Whole-slot addressing skips unused high bits instead of allowing the next entry to
        // straddle a word boundary.
        var arrayIndex = index / this.version.entriesPerWord;
        var offset = (index % this.version.entriesPerWord) * this.version.bits;
        return (this.words[arrayIndex] >>> offset) & this.version.maxEntryIndex;
    }

    /**
     * Returns a packed array with the same layout and copied words. The new array retains
     * no mutable word storage from this instance, so either copy can be changed
     * independently.
     */
    @Override
    public BitArray copy() {
        return new PaddedBitArray(this.version, this.size, Arrays.copyOf(this.words, this.words.length));
    }

    /**
     * Returns whether another {@code PaddedBitArray} has the same version, size, and packed words.
     * Comparison includes unused padding bits when present, rather than comparing only
     * values returned by logical reads.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaddedBitArray that)) return false;
        return size == that.size && version == that.version && Arrays.equals(words, that.words);
    }

    /**
     * Returns a hash of the version, logical size, and packed words. Because {@code words}
     * is mutable and exposed, callers must avoid changing it while this array is a hash
     * key.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(version, size, Arrays.hashCode(words));
    }
}
