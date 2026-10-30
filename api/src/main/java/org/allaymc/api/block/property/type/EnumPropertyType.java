package org.allaymc.api.block.property.type;

import lombok.Getter;
import org.allaymc.api.utils.Utils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;

/**
 * {@code EnumPropertyType} describes a block property whose domain is one Java enum
 * and caches wrappers for its constants. Packed indices use enum ordinals, while
 * serialized values use lowercase constant names with locale-independent
 * conversion; external strings are matched through the same locale-independent
 * naming convention.
 *
 * @author daoge_cmd
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseBlockPropertyType<T> {

    /**
     * {@code cachedValues} retains wrappers built from the original enum constants.
     * Later changes to the exposed valid-value list do not rebuild this lookup.
     */
    private final EnumMap<T, EnumPropertyValue> cachedValues;
    /**
     * Accepted enum runtime class.
     */
    private final Class<T> enumClass;

    /**
     * Creates the enum domain and prepares a wrapper for each constant. The enum
     * class must have at least one constant, and the default must be non-{@code
     * null}; this constructor does not verify default membership.
     */
    private EnumPropertyType(String name, Class<T> enumClass, T defaultData) {
        super(name, Arrays.asList(enumClass.getEnumConstants()), defaultData, Utils.computeRequiredBits(enumClass.getEnumConstants().length));
        this.enumClass = enumClass;
        var map = new HashMap<T, EnumPropertyValue>();
        for (var value : validValues) {
            map.put(value, new EnumPropertyValue(value));
        }
        cachedValues = new EnumMap<>(map);
    }

    /**
     * Creates a distinct property backed by the supplied nonempty enum class. The
     * default must be non-{@code null}, and callers must preserve the exposed
     * domain list because cached wrappers reflect its original constants.
     */
    public static <T extends Enum<T>> EnumPropertyType<T> of(String name, Class<T> enumClass, T defaultData) {
        return new EnumPropertyType<>(name, enumClass, defaultData);
    }

    /**
     * Returns the enum storage category used by this property implementation.
     */
    @Override
    public Type getType() {
        return Type.ENUM;
    }

    /**
     * Returns the cached wrapper for the supplied enum constant. A {@code null} or
     * unrecognized lookup key returns {@code null}; successful lookups reuse the
     * wrapper owned by this property instance.
     */
    @Override
    public EnumPropertyValue createValue(T value) {
        return cachedValues.get(value);
    }

    /**
     * Converts a constant of the configured enum or a case-insensitive
     * constant-name string into its cached wrapper. Strings use {@code
     * Locale.ROOT}; invalid names and other input types throw {@code
     * IllegalArgumentException}.
     */
    @Override
    public EnumPropertyValue tryCreateValue(Object value) {
        if (enumClass.isInstance(value)) {
            return cachedValues.get(enumClass.cast(value));
        } else if (value instanceof String str) {
            // Use a locale-independent name conversion so host locale cannot change
            // which serialized enum name selects a cached wrapper.
            return cachedValues.get(Enum.valueOf(enumClass, str.toUpperCase(Locale.ROOT)));
        }
        throw new IllegalArgumentException("Invalid value for enum property type: " + value);
    }

    /**
     * {@code EnumPropertyValue} represents an enum constant belonging to its
     * enclosing property domain.
     */
    public final class EnumPropertyValue extends BlockPropertyValue<T, EnumPropertyType<T>, String> {
        /**
         * Locale-independent lowercase constant name.
         */
        @Getter
        private final String serializedValue;

        /**
         * Creates a wrapper for the enclosing enum property and prepares its
         * lowercase NBT name.
         */
        EnumPropertyValue(T value) {
            super(EnumPropertyType.this, value);
            // Serialized enum names use locale-independent lowercase so state NBT
            // and its hash do not vary with the host locale.
            this.serializedValue = value.name().toLowerCase(Locale.ROOT);
        }

        /**
         * Returns the wrapped enum constant's ordinal for packed state-key lookup.
         */
        @Override
        public int getIndex() {
            return value.ordinal();
        }

        /**
         * Returns a diagnostic description containing this enum property's name and
         * constant.
         */
        @Override
        public String toString() {
            return "EnumPropertyValue(name=" + name + ", value=" + value + ")";
        }
    }
}
