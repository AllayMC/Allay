package cn.allay.block.property.type;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {

    private final BlockPropertyValue<Integer, IntPropertyType>[] cachedValues;
    @Getter
    private final int min;
    @Getter
    private final int max;

    private IntPropertyType(String name, int min, int max, Integer defaultData) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultData);
        this.min = min;
        this.max = max;
        cachedValues = new BlockPropertyValue[max + 1 - min];
        for (int i = min; i <= max; i++) {
            BlockPropertyValue<Integer, IntPropertyType> property = new BlockPropertyValue<>(this, i);
            cachedValues[i] = property;
        }
    }

    public static IntPropertyType createType(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, min, max, defaultData);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public BlockPropertyValue<Integer, ? extends BlockPropertyType<Integer>> createValue(Integer value) {
        return cachedValues[value - min];
    }

    @Override
    public BlockPropertyValue<Integer, ? extends BlockPropertyType<Integer>> tryCreateValue(Object value) {
        if (value instanceof Integer integer) {
            return cachedValues[integer - min];
        } else throw new IllegalArgumentException("Invalid value for int property type: " + value);
    }
}
