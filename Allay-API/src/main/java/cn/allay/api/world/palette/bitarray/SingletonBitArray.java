package cn.allay.api.world.palette.bitarray;

import io.netty.buffer.ByteBuf;

/**
 * @author JukeboxMC | daoge_cmd <br>
 * @date 2023/4/14 <br>
 * Allay Project <br>
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
    public int getSize() {
        return 1;
    }

    @Override
    public int[] getWords() {
        return EMPTY_ARRAY;
    }

    @Override
    public BitArrayVersion getVersion() {
        return BitArrayVersion.V0;
    }

    @Override
    public SingletonBitArray copy() {
        return new SingletonBitArray();
    }
}
