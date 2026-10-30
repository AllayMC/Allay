package org.allaymc.api.entity.property.type;

import java.util.Arrays;
import java.util.Locale;

/**
 * {@code EnumPropertyType} describes a named enum entity property for default
 * lookup, persistence, and client synchronization. The enum declaration order
 * supplies ordinal indices, while NBT serialization uses names lowered with
 * {@code Locale.ROOT}; unrecognized serialized names select the configured
 * default.
 *
 * @author daoge_cmd
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseEntityPropertyType<T> {

    /**
     * Creates an enum definition backed by an array of the class's constants.
     * The resulting value list permits entry replacement but no size changes;
     * the superclass rejects a {@code null} default.
     */
    private EnumPropertyType(String name, Class<T> enumClass, T defaultValue) {
        super(name, Arrays.asList(enumClass.getEnumConstants()), defaultValue);
    }

    /**
     * Creates a named enum property using declaration-order constants and the
     * supplied default. The definition retains its own array-backed list;
     * callers must preserve that list's entries so ordinal indices and
     * serialized choices remain consistent.
     */
    public static <T extends Enum<T>> EnumPropertyType<T> of(String name, Class<T> enumClass, T defaultValue) {
        return new EnumPropertyType<>(name, enumClass, defaultValue);
    }

    /**
     * Returns the enum representation category for this property definition.
     */
    @Override
    public Type getType() {
        return Type.ENUM;
    }

    /**
     * Returns the declaration ordinal of this property's configured default
     * constant.
     */
    public int defaultIndex() {
        return getDefaultValue().ordinal();
    }

    /**
     * Returns the declaration ordinal of the supplied enum constant.
     */
    public int findIndex(T value) {
        return value.ordinal();
    }

    /**
     * Returns the enum constant stored at the supplied value-list index. The
     * index must be within the current list; replacing list entries through
     * the exposed getter changes this lookup.
     *
     * @throws IndexOutOfBoundsException if {@code index} is outside the value list
     */
    public T findValue(int index) {
        return validValues.get(index);
    }

    /**
     * Returns the first enum constant whose name matches the input without
     * case sensitivity. Unknown names and {@code null} select the configured
     * default; leading or trailing whitespace is not removed.
     */
    public T deserializeValue(String serializedValue) {
        for (var value : validValues) {
            if (value.name().equalsIgnoreCase(serializedValue)) {
                return value;
            }
        }
        // Keep older or unrecognized persisted names usable by selecting the
        // property definition's default instead of inserting an unknown enum value.
        return getDefaultValue();
    }

    /**
     * Returns the supplied enum constant's name lowercased with
     * {@code Locale.ROOT}. Persisted names therefore stay independent of the
     * server's default locale and match the name-based deserialization path
     * rather than storing ordinal positions.
     */
    public String serializeValue(T value) {
        // Use locale-independent lowercase names so persistence does not change
        // with the process locale and later name matching can recover the enum constant.
        return value.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Returns a new array containing lowercase names in the current value-list
     * order. The returned array is independent of the list, while replacements
     * in that list affect later calls.
     */
    public String[] serializedValues() {
        // Use the same locale-independent names as single-value persistence so
        // clients receive enum choices in the current definition list's matching order.
        return validValues.stream().map(v -> v.name().toLowerCase(Locale.ROOT)).toArray(String[]::new);
    }
}
