package cn.allay.block.property.type;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {
    private IntPropertyType(String name, List<Integer> validValue, Integer defaultData) {
        super(name, validValue, defaultData);
    }

    public static IntPropertyType createType(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, IntStream.range(min, max + 1).boxed().toList(), defaultData);
    }
}
