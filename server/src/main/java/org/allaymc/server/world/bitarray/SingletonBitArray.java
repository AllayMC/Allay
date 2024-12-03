package org.allaymc.server.world.bitarray;

/**
 * @author JukeboxMC | daoge_cmd
 */
public record SingletonBitArray() implements BitArray {
    public static SingletonBitArray INSTANCE = new SingletonBitArray();
    private static final int[] EMPTY_ARRAY = new int[0];

    @Override
    public void set(int index, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int[] words() {
        return EMPTY_ARRAY;
    }

    @Override
    public BitArrayVersion version() {
        return BitArrayVersion.V0;
    }

    @Override
    public SingletonBitArray copy() {
        return new SingletonBitArray();
    }
}
