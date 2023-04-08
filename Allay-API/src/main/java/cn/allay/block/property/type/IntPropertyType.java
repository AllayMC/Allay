package cn.allay.block.property.type;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {

    private final BlockProperty<Integer, IntPropertyType>[] cachedValues;
    @Getter
    private final int min;
    @Getter
    private final int max;

    private IntPropertyType(String name, int min, int max, Integer defaultData) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultData);
        this.min = min;
        this.max = max;
        cachedValues = new BlockProperty[max + 1 - min];
        for (int i = min; i <= max; i++) {
            BlockProperty<Integer, IntPropertyType> property = new BlockProperty<>(this, i);
            cachedValues[i] = property;
        }
    }

    public static IntPropertyType createType(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, min, max, defaultData);
    }

    @Override
    public BlockProperty<Integer, ? extends BlockPropertyType<Integer>> createProperty(Integer value) {
        return cachedValues[value - min];
    }

    @Override
    public BlockProperty<Integer, ? extends BlockPropertyType<Integer>> tryCreateProperty(Object value) {
        if (value instanceof Integer integer) {
            return cachedValues[integer - min];
        } else throw new IllegalArgumentException("Invalid value for int property type: " + value);
    }
}
