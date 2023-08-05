package cn.allay.api.world.palette.bitarray;

import io.netty.buffer.ByteBuf;

/**
 * Allay Project 2023/4/14
 *
 * @author JukeboxMC | daoge_cmd
 */
public final class SingletonBitArray implements BitArray {

    private static final int[] EMPTY_ARRAY = new int[0];

    @Override
    public void set(int index, int value) {
    }

    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public void writeSizeToNetwork(ByteBuf buffer, int size) {
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
