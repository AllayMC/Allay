package org.allaymc.api.world.heightmap;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Allay Project 2023/5/30
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

    public short get(int x, int z) {
        return heights[(x << 4) | z];
    }

    public void set(int x, int z, short height) {
        heights[(x << 4) | z] = height;
    }

    public void fill(short[] heights) {
        System.arraycopy(heights, 0, this.heights, 0, 256);
    }
}
