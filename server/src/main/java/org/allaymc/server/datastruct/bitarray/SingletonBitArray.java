package org.allaymc.server.datastruct.bitarray;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JukeboxMC | daoge_cmd
 */
@Slf4j
public record SingletonBitArray() implements BitArray {
    private static final int[] EMPTY_ARRAY = new int[0];
    public static SingletonBitArray INSTANCE = new SingletonBitArray();

    @Override
    public void set(int index, int value) {
        if (value == 0) {
            // Set value to zero is acceptable because
            // the get() method will always return zero
            return;
        }

        log.error("Attempted to set non-zero value in singleton bit array");
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
