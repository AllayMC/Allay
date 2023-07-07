package cn.allay.api.utils;

import cn.allay.api.block.property.type.BlockPropertyType;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Range;

import java.util.List;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    public static byte[] appendBytes(byte[] bytes1, byte[]... bytes2) {
        int length = bytes1.length;
        for (byte[] bytes : bytes2) {
            length += bytes.length;
        }

        byte[] appendedBytes = new byte[length];
        System.arraycopy(bytes1, 0, appendedBytes, 0, bytes1.length);
        int index = bytes1.length;

        for (byte[] b : bytes2) {
            System.arraycopy(b, 0, appendedBytes, index, b.length);
            index += b.length;
        }
        return appendedBytes;
    }

    public static int computeRequiredBits(@Range(from = 1, to = Integer.MAX_VALUE) int size) {
        if (size <= 2)
            return 1;
        int bits = 2;
        while (size > (1 << bits)) {
            bits++;
        }
        return bits;
    }
}
