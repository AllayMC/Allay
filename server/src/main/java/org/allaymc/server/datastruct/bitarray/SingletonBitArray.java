package org.allaymc.server.datastruct.bitarray;

import lombok.extern.slf4j.Slf4j;

/**
 * {@code SingletonBitArray} represents a uniform palette containing only index zero
 * without allocating packed words. Reads accept every index and return zero, even though
 * {@code size()} reports one. Zero writes are harmless, while any nonzero write is logged
 * and rejected.
 * @author JukeboxMC | daoge_cmd
 */
@Slf4j
public record SingletonBitArray() implements BitArray {
    /**
     * {@code EMPTY_ARRAY} is the shared zero-length storage returned by {@code words()}.
     * All instances reuse it because no logical value needs an encoded word.
     */
    private static final int[] EMPTY_ARRAY = new int[0];
    /**
     * {@code INSTANCE} supplies the singleton used by {@code V0} array creation. The field
     * is publicly replaceable, while each instance itself contains no mutable entry state.
     */
    public static SingletonBitArray INSTANCE = new SingletonBitArray();

    /**
     * Accepts writes of zero without changing any stored state. Any nonzero {@code value}
     * is logged and throws {@code UnsupportedOperationException}; {@code index} is ignored
     * because the representation has no independent slots.
     */
    @Override
    public void set(int index, int value) {
        if (value == 0) {
            // Zero writes need no storage update because every read already returns the sole
            // value represented by this layout.
            return;
        }

        log.error("Attempted to set non-zero value in singleton bit array");
        throw new UnsupportedOperationException();
    }

    /**
     * Returns zero for every supplied logical index without checking its bounds. This
     * representation has no independent entry slots, so all section positions resolve to
     * the same default palette index.
     */
    @Override
    public int get(int index) {
        return 0;
    }

    /**
     * Returns one as this zero-bit representation's reported logical size. Reads still
     * accept every index and return zero, so section callers must not interpret this count
     * as the number of readable positions.
     */
    @Override
    public int size() {
        return 1;
    }

    /**
     * Returns the shared empty array because this representation encodes no words.
     */
    @Override
    public int[] words() {
        return EMPTY_ARRAY;
    }

    /**
     * Returns {@code V0}, the palette version with no stored index bits.
     */
    @Override
    public BitArrayVersion version() {
        return BitArrayVersion.V0;
    }

    /**
     * Returns a new zero-bit instance sharing the same empty word array.
     */
    @Override
    public SingletonBitArray copy() {
        return new SingletonBitArray();
    }
}
