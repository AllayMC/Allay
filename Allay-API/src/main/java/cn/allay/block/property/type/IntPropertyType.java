package cn.allay.block.property.type;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {

    private final IntPropertyValue[] cachedValues;
    @Getter
    private final int min;
    @Getter
    private final int max;

    private IntPropertyType(String name, int min, int max, Integer defaultData) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultData);
        this.min = min;
        this.max = max;
        cachedValues = new IntPropertyValue[max + 1 - min];
        for (int i = min; i <= max; i++) {
            IntPropertyValue value = new IntPropertyValue(i);
            cachedValues[i] = value;
        }
    }

    public static IntPropertyType of(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, min, max, defaultData);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public IntPropertyValue createValue(Integer value) {
        return cachedValues[value - min];
    }

    @Override
    public IntPropertyValue tryCreateValue(Object value) {
        if (value instanceof Number number) {
            return cachedValues[number.intValue() - min];
        } else throw new IllegalArgumentException("Invalid value for int property type: " + value);
    }

    final class IntPropertyValue extends BlockPropertyValue<Integer, IntPropertyType, Integer> {

        IntPropertyValue(Integer value) {
            super(IntPropertyType.this, value);
        }

        @Override
        public Integer getSerializedValue() {
            return value;
        }

        @Override
        public String toString() {
            return "IntPropertyValue(name=" + name + ", value=" + value + ")";
        }
    }
}
