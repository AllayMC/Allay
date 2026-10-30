package org.allaymc.server.world.chunk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

/**
 * {@code HeightMap} stores one signed block height for each horizontal column in a chunk.
 * Coordinates use X-major ordering with Z in the low four bits; the array
 * constructor and generated getter share storage directly, so callers control
 * validation and synchronization. Callers of the generated array constructor
 * must provide at least 256 shorts for the complete coordinate range; the
 * constructor does not check the array's capacity.
 *
 * @author Cool_Loong
 */
@Getter
@AllArgsConstructor
public final class HeightMap {
    /**
     * {@code heights} holds X-major signed block heights. The generated getter
     * exposes the retained mutable array directly to codecs and other callers.
     */
    private final short[] heights;

    /**
     * Creates an independent height map initialized to zero for every column.
     */
    public HeightMap() {
        this((short) 0);
    }

    /**
     * Creates an independent height map filled with the supplied signed block
     * height.
     */
    public HeightMap(short defaultValue) {
        this.heights = new short[256];
        Arrays.fill(this.heights, defaultValue);
    }

    /**
     * Returns the X-major array index for local coordinates from zero through
     * fifteen. Inputs are not validated; supplying coordinates outside that
     * range can alias another column or exceed storage.
     */
    public static int computeIndex(int x, int z) {
        return (x << 4) | z;
    }

    /**
     * Returns the stored signed height for local horizontal coordinates from
     * zero through fifteen. The result is a cached value; this container does
     * not inspect blocks or recalculate heights.
     */
    public short get(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        return heights[computeIndex(x, z)];
    }

    /**
     * Returns the signed height at an X-major index from zero through 255. The
     * index is used directly, and the stored value is not recomputed from chunk
     * blocks.
     */
    public short get(int index) {
        return heights[index];
    }

    /**
     * Stores a signed block height at local horizontal coordinates from zero
     * through fifteen. This container does not validate dimension bounds or
     * notify the owning chunk's dirty-column tracking.
     */
    public void set(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, short height) {
        heights[computeIndex(x, z)] = height;
    }

    /**
     * Stores a signed block height at an X-major index from zero through 255.
     * The index is used directly, and callers coordinate this update with chunk
     * height-cache state.
     */
    public void set(int index, short height) {
        heights[index] = height;
    }

    /**
     * Copies the first 256 supplied heights into this map's existing storage.
     * The input must contain at least 256 entries; later changes to the input
     * array do not affect the map.
     */
    public void fill(short[] heights) {
        System.arraycopy(heights, 0, this.heights, 0, 256);
    }
}
