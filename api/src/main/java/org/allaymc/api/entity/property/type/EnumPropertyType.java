package org.allaymc.api.entity.property.type;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author daoge_cmd
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseEntityPropertyType<T> {

    private EnumPropertyType(String name, Class<T> enumClass, T defaultValue) {
        super(name, Arrays.asList(enumClass.getEnumConstants()), defaultValue);
    }

    public static <T extends Enum<T>> EnumPropertyType<T> of(String name, Class<T> enumClass, T defaultValue) {
        return new EnumPropertyType<>(name, enumClass, defaultValue);
    }

    @Override
    public Type getType() {
        return Type.ENUM;
    }

    /**
     * Gets the index of the default value (ordinal).
     *
     * @return the default index
     */
    public int defaultIndex() {
        return getDefaultValue().ordinal();
    }

    /**
     * Finds the index of the given enum value (ordinal).
     *
     * @param value the value to find
     * @return the ordinal index
     */
    public int findIndex(T value) {
        return value.ordinal();
    }

    /**
     * Gets the enum value at the given index.
     *
     * @param index the ordinal index
     * @return the enum value
     */
    public T findValue(int index) {
        return validValues.get(index);
    }

    /**
     * Gets the enum value from a serialized (lowercase) value.
     *
     * @param serializedValue the lowercase serialized value
     * @return the enum value, or the default value if not found
     */
    public T deserializeValue(String serializedValue) {
        for (var value : validValues) {
            if (value.name().equalsIgnoreCase(serializedValue)) {
                return value;
            }
        }
        return getDefaultValue();
    }

    /**
     * Gets the serialized (lowercase) value of the given enum value.
     *
     * @param value the enum value
     * @return the lowercase name
     */
    public String serializeValue(T value) {
        return value.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Gets all serialized (lowercase) enum values as a string array, in ordinal order.
     *
     * @return the serialized names
     */
    public String[] serializedValues() {
        return validValues.stream().map(v -> v.name().toLowerCase(Locale.ROOT)).toArray(String[]::new);
    }
}
