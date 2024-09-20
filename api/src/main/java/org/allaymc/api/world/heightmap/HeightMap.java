package org.allaymc.api.world.heightmap;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HeightMap stores the height of each pos in a chunk.
 *
 * @author Cool_Loong
 */
@Getter
@AllArgsConstructor
public final class HeightMap {
    private final short[] heights;

    public HeightMap() {
        this(new short[256]);
    }

    /**
     * Get the height of the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @return the height of the pos.
     */
    public short get(int x, int z) {
        return heights[(x << 4) | z];
    }

    /**
     * Set the height of the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param z the z coordinate of the pos.
     * @param height the height of the pos.
     */
    public void set(int x, int z, short height) {
        heights[(x << 4) | z] = height;
    }

    /**
     * Fill the height map with the specified heights.
     *
     * @param heights the heights to fill.
     */
    public void fill(short[] heights) {
        System.arraycopy(heights, 0, this.heights, 0, 256);
    }
}
