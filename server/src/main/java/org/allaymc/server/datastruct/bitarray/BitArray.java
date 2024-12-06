package org.allaymc.server.datastruct.bitarray;

/**
 * @author JukeboxMC | daoge_cmd
 */
public interface BitArray {

    void set(int index, int value);

    int get(int index);

    int size();

    int[] words();

    BitArrayVersion version();

    BitArray copy();
}