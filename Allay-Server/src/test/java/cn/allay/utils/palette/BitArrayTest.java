package cn.allay.utils.palette;

import cn.allay.utils.palette.bitarray.BitArrayVersion;
import cn.allay.utils.palette.bitarray.PaddedBitArray;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/14 <br>
 * Allay Project <br>
 */
public class BitArrayTest {
    public static void main(String[] args) {
        var padded = new PaddedBitArray(BitArrayVersion.V2, 4096, new int[256]);
        System.out.println();
    }
}
