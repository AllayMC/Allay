package cn.allay.api.block.property.type;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseBlockPropertyType<T> {

    private final EnumMap<T, EnumPropertyValue> cachedValues;
    private final Class<T> enumClass;

    private EnumPropertyType(String name, Class<T> enumClass, T defaultData) {
        super(name, Arrays.asList(enumClass.getEnumConstants()), defaultData);
        this.enumClass = enumClass;
        var map = new HashMap<T, EnumPropertyValue>();
        for (var value : validValues) {
            map.put(value, new EnumPropertyValue(value));
        }
        cachedValues = new EnumMap<>(map);
    }

    public static <T extends Enum<T>> EnumPropertyType<T> of(String name, Class<T> enumClass, T defaultData) {
        return new EnumPropertyType<>(name, enumClass, defaultData);
    }

    @Override
    public Type getType() {
        return Type.ENUM;
    }

    @Override
    public EnumPropertyValue createValue(T value) {
        return cachedValues.get(value);
    }

    @Override
    public EnumPropertyValue tryCreateValue(Object value) {
        if (enumClass.isInstance(value)) {
            return cachedValues.get(enumClass.cast(value));
        } else if (value instanceof String str) {
            return cachedValues.get(Enum.valueOf(enumClass, str.toUpperCase()));
        }
        throw new IllegalArgumentException("Invalid value for enum property type: " + value);
    }

    final class EnumPropertyValue extends BlockPropertyValue<T, EnumPropertyType<T>, String> {

        private final String serializedValue;

        EnumPropertyValue(T value) {
            super(EnumPropertyType.this, value);
            serializedValue = value.name().toLowerCase();
        }

        @Override
        public String getSerializedValue() {
            return serializedValue;
        }

        @Override
        public String toString() {
            return "EnumPropertyValue(name=" + name + ", value=" + value + ")";
        }
    }
}
