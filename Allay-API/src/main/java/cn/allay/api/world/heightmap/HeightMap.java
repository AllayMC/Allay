package cn.allay.api.world.heightmap;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public final class HeightMap {
    private final short[] heights;

    public HeightMap() {
        this.heights = new short[256];
    }

    public HeightMap(short[] data) {
        this.heights = data;
    }

    public short get(int x, int z) {
        return heights[(z << 4) + x];
    }

    public void set(int x, int z, short height) {
        heights[(z << 4) + x] = height;
    }

    public void fill(short[] heights) {
        System.arraycopy(heights, 0, this.heights, 0, 256);
    }

    public short[] getHeights() {
        return this.heights;
    }
}
