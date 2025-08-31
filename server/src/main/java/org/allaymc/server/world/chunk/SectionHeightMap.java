package org.allaymc.server.world.chunk;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Range;

/**
 * SectionHeightMap stores the height of each pos in a chunk section
 *
 * @author Cool_Loong
 */
@AllArgsConstructor
public final class SectionHeightMap {
    private final byte[] heights;

    public SectionHeightMap() {
        this.heights = new byte[128];
    }

    /**
     * Get the height of the specified position.
     *
     * @param x the x coordinate of the pos
     * @param z the z coordinate of the pos
     *
     * @return the height of the pos
     */
    public @Range(from = 0, to = 15) byte get(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        int index = (x * 16 + z) / 2;
        int shift = (x * 16 + z) % 2 == 0 ? 0 : 4;

        return (byte) ((heights[index] >> shift) & 0xF);
    }

    /**
     * Set the height of the specified position.
     *
     * @param x      the x coordinate of the pos
     * @param z      the z coordinate of the pos
     * @param height the height of the pos
     */
    public void set(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) byte height) {
        int index = (x * 16 + z) / 2;
        int shift = (x * 16 + z) % 2 == 0 ? 0 : 4;

        heights[index] = (byte) ((heights[index] & ~(0xF << shift)) | (height << shift));
    }
}
