package org.allaymc.server.world;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
        this((short) 0);
    }

    public HeightMap(short defaultValue) {
        this.heights = new short[256];
        Arrays.fill(this.heights, defaultValue);
    }

    /**
     * Compute the index of the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return the index of the pos.
     */
    public static int computeIndex(int x, int z) {
        return (x << 4) | z;
    }

    /**
     * Get the height of the specified position.
     *
     * @param x the x coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return the height of the pos.
     */
    public short get(int x, int z) {
        return heights[computeIndex(x, z)];
    }

    /**
     * Get the height of the specified position.
     *
     * @param index the index of the pos.
     *
     * @return the height of the pos.
     */
    public short get(int index) {
        return heights[index];
    }

    /**
     * Set the height of the specified position.
     *
     * @param x      the x coordinate of the pos.
     * @param z      the z coordinate of the pos.
     * @param height the height of the pos.
     */
    public void set(int x, int z, short height) {
        heights[computeIndex(x, z)] = height;
    }

    /**
     * Set the height of the specified position.
     *
     * @param index  the index of the pos.
     * @param height the height of the pos.
     */
    public void set(int index, short height) {
        heights[index] = height;
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
