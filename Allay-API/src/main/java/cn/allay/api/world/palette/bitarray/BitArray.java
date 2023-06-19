package cn.allay.api.world.palette.bitarray;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * Author: JukeboxMC | daoge_cmd <br>
 * Date: 2023/4/14 <br>
 * Allay Project <br>
 */
public interface BitArray {

    void set(int index, int value);

    int get(int index);

    default void writeSizeToNetwork(ByteBuf buffer, int size) {
        VarInts.writeInt(buffer, size);
    }

    default int readSizeFromNetwork(ByteBuf buffer) {
        return VarInts.readInt(buffer);
    }

    int getSize();

    int[] getWords();

    BitArrayVersion getVersion();

    BitArray copy();

}