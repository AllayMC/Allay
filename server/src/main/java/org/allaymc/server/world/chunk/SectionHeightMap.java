package org.allaymc.server.world.chunk;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Range;

/**
 * {@code SectionHeightMap} packs
 * one four-bit height for every horizontal column in a section. X-major
 * column indices share bytes in pairs, with the even column in the low nibble
 * and the odd column in the high nibble.
 *
 * <p>
 * Reading masks out the neighboring column, while writing clears only the
 * selected nibble before inserting its replacement. Coordinates and heights
 * must remain in their annotated zero-through-fifteen ranges because this
 * container does not validate them. The generated array constructor retains its
 * input, so external array mutation and unsynchronized writes can affect both
 * columns sharing a byte. Callers of that constructor must provide at least
 * 128 bytes for the complete coordinate range; the constructor does not check
 * the array's capacity.
 *
 * @author Cool_Loong
 */
@AllArgsConstructor
public final class SectionHeightMap {
    /**
     * {@code heights} holds the retained packed height bytes. Callers coordinate
     * mutation because adjacent columns share one byte and updates are unsynchronized.
     */
    private final byte[] heights;

    /**
     * Creates independent packed storage with every section height initially
     * zero.
     */
    public SectionHeightMap() {
        this.heights = new byte[128];
    }

    /**
     * Returns the four-bit height for valid section-local horizontal
     * coordinates. Even X-major column indices use the low nibble, and odd
     * indices use the high nibble; masking excludes the adjacent column sharing
     * the same byte. Inputs are not checked, so callers must remain within zero
     * through fifteen on both axes. Concurrent writes require external
     * synchronization because both heights occupy one storage element.
     */
    public @Range(from = 0, to = 15) byte get(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        // X-major neighboring columns share a byte; selecting one nibble and
        // masking its result prevents the other column from affecting the
        // returned height.
        int index = (x * 16 + z) / 2;
        int shift = (x * 16 + z) % 2 == 0 ? 0 : 4;

        return (byte) ((heights[index] >> shift) & 0xF);
    }

    /**
     * Replaces one four-bit height while retaining the adjacent column in the
     * same byte. Coordinates and height must each be between zero and fifteen;
     * the implementation does not mask an oversized height before insertion.
     * Clearing only the selected nibble preserves its neighbor for valid
     * inputs. Callers must serialize concurrent updates to a shared byte
     * because the read-modify-write sequence is not atomic.
     */
    public void set(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) byte height) {
        // The read-modify-write retains the other column's nibble; invalid heights
        // can spill into that neighbor because only the destination is masked.
        int index = (x * 16 + z) / 2;
        int shift = (x * 16 + z) % 2 == 0 ? 0 : 4;

        heights[index] = (byte) ((heights[index] & ~(0xF << shift)) | (height << shift));
    }
}
