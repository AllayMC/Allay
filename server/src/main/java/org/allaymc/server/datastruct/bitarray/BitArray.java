package org.allaymc.server.datastruct.bitarray;

/**
 * {@code BitArray} stores palette indices using a versioned packed-word representation.
 * Ordinary implementations expose a fixed logical size and mutable backing words. The
 * zero-bit singleton represents only zero and accepts any read index, allowing uniform
 * palettes to omit word storage.
 * @author JukeboxMC | daoge_cmd
 */
public interface BitArray {

    /**
     * Stores {@code value} at the supplied logical entry index. Packed implementations
     * validate the index and the version's unsigned value range; the zero-bit singleton
     * accepts zero and rejects nonzero values.
     */
    void set(int index, int value);

    /**
     * Returns the unsigned palette index at the supplied logical entry. Packed
     * implementations reject indices outside their logical size, while the zero-bit
     * singleton returns zero for every supplied index.
     */
    int get(int index);

    /**
     * Returns the logical entry count reported by this bit array.
     */
    int size();

    /**
     * Returns the backing packed words used for palette serialization. Mutable
     * implementations expose the actual array, so external writes change subsequent reads
     * and must preserve the version's layout rules.
     */
    int[] words();

    /**
     * Returns the packing version defining entry width and word layout.
     */
    BitArrayVersion version();

    /**
     * Returns a bit array with independently mutable packed-word storage. Implementations
     * preserve the version and logical values; the singleton has no mutable entries and
     * may share its empty words array.
     */
    BitArray copy();
}
