package cn.allay.server.world.chunk;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public final class HeightMap {
    private final int[] heights;

    public HeightMap() {
        this.heights = new int[256];
    }

    public HeightMap(long[] compact) {
        this.heights = unpack(compact, 9);
    }

    public int get(int x, int z) {
        return heights[(z << 4) + x];
    }

    public void set(int x, int z, int height) {
        heights[(z << 4) + x] = height;
    }

    public void fill(int[] heights) {
        System.arraycopy(heights, 0, this.heights, 0, 256);
    }

    /**
     * Unpacks int values of 'lengthInBits' bits from a long array.
     * this method will produce unused bits and do not overflow remaining bits to the next long.
     * <p>
     * (ie 2 >32 bit long values will produce two longs, but the highest bits of each long will be unused)
     */
    public static int[] unpack(long[] longs, int lengthInBits) {
        int intPerLong = (int) Math.floor(64.0 / lengthInBits);
        int intCount = (int) Math.ceil((double) longs.length * intPerLong);
        int[] ints = new int[intCount];
        int intPerLongCeil = (int) Math.ceil(intPerLong);
        long mask = (1L << lengthInBits) - 1L;
        for (int i = 0; i < ints.length; i++) {
            int longIndex = i / intPerLongCeil;
            int subIndex = i % intPerLongCeil;
            int value = (int) ((longs[longIndex] >>> (subIndex * lengthInBits)) & mask);
            ints[i] = value;
        }
        return ints;
    }

    /**
     * Packs ints into a long array. Produces unused bits and does not partially overflow to next long on boundaries.
     */
    public static long[] pack(int[] ints, int lengthInBits) {
        int intPerLong = (int) Math.floor(64.0 / lengthInBits);
        int longCount = (int) Math.ceil((double) ints.length / intPerLong);
        long[] longs = new long[longCount];
        long mask = (1L << lengthInBits) - 1L;
        for (int i = 0; i < longs.length; i++) {
            long l = 0L;
            for (int intIndex = 0; intIndex < intPerLong; intIndex++) {
                int bitIndex = intIndex * lengthInBits;
                int intActualIndex = intIndex + i * intPerLong;
                if (intActualIndex < ints.length) {
                    long value = ((long) ints[intActualIndex] & mask) << bitIndex;
                    l |= value;
                }
            }
            longs[i] = l;
        }
        return longs;
    }
}
