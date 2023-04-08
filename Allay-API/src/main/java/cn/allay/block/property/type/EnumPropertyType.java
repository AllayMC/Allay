package cn.allay.block.property.type;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseBlockPropertyType<T> {

    private final EnumMap<T, BlockProperty<T, EnumPropertyType<T>>> cachedValues;
    private final Class<T> enumClass;

    private EnumPropertyType(String name, Class<T> enumClass, T defaultData) {
        super(name, Arrays.asList(enumClass.getEnumConstants()), defaultData);
        this.enumClass = enumClass;
        var map = new HashMap<T, BlockProperty<T, EnumPropertyType<T>>>();
        for (var value : validValues) {
            map.put(value, new BlockProperty<>(this, value));
        }
        cachedValues = new EnumMap<>(map);
    }

    public static <T extends Enum<T>> EnumPropertyType<T> createType(String name, Class<T> enumClass, T defaultData) {
        return new EnumPropertyType<>(name, enumClass, defaultData);
    }

    @Override
    public BlockProperty<T, ? extends BlockPropertyType<T>> createProperty(T value) {
        return cachedValues.get(value);
    }

    @Override
    public BlockProperty<T, ? extends BlockPropertyType<T>> tryCreateProperty(Object value) {
        if (enumClass.isInstance(value)) {
            return cachedValues.get(enumClass.cast(value));
        } else if (value instanceof String str) {
            return cachedValues.get(Enum.valueOf(enumClass, str.toUpperCase()));
        }
        throw new IllegalArgumentException("Invalid value for enum property type: " + value);
    }
}
