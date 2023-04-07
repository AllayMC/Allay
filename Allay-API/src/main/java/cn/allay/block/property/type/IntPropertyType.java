package cn.allay.block.property.type;

import lombok.Getter;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
@Getter
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {

    private final int min;
    private final int max;

    private IntPropertyType(String name, int min, int max, Integer defaultData) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultData);
        this.min = min;
        this.max = max;
    }

    public static IntPropertyType createType(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, min, max, defaultData);
    }

    @Override
    public boolean checkValid(Integer value) {
        int v = value;
        return v >= min && v <= max;
    }
}
