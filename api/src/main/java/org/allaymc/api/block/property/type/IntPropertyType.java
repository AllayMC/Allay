package org.allaymc.api.block.property.type;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.utils.Utils;

import java.util.stream.IntStream;

/**
 * {@code IntPropertyType} describes a contiguous integer block property with cached
 * value wrappers and minimum-relative packed indices. Serialized values retain the
 * original integers. The current cache initialization uses raw values as array
 * indices, so nonempty domains require a zero minimum to construct successfully.
 *
 * @author daoge_cmd
 */
@Getter
public final class IntPropertyType extends BaseBlockPropertyType<Integer> {
    /**
     * {@code cachedValues} contains the wrappers returned by value conversion.
     * Lookup subtracts {@code min}, while constructor population currently indexes
     * this array with each raw value.
     */
    @Getter(AccessLevel.NONE)
    private final IntPropertyValue[] cachedValues;
    /**
     * {@code min} and {@code max} are the inclusive domain bounds. {@code min}
     * supplies the packed-index origin, while {@code max} limits the values
     * enumerated during wrapper initialization.
     */
    private final int min, max;

    /**
     * Creates the inclusive integer domain and its cached wrappers. A nonempty
     * range currently requires {@code min} to be {@code 0} because initialization
     * indexes the cache with raw values; the non-{@code null} default is not
     * range-validated.
     */
    private IntPropertyType(String name, int min, int max, Integer defaultData) {
        super(name, IntStream.range(min, max + 1).boxed().toList(), defaultData, Utils.computeRequiredBits(max - min + 1));
        this.min = min;
        this.max = max;

        this.cachedValues = new IntPropertyValue[max + 1 - min];
        for (int i = min; i <= max; i++) {
            this.cachedValues[i] = new IntPropertyValue(i);
        }
    }

    /**
     * Creates a distinct integer property with inclusive bounds and a non-{@code
     * null} default. Nonempty ranges currently need a zero minimum, and callers
     * must supply a default inside the intended domain.
     */
    public static IntPropertyType of(String name, int min, int max, Integer defaultData) {
        return new IntPropertyType(name, min, max, defaultData);
    }

    /**
     * Returns the integer storage category used by this property implementation.
     */
    @Override
    public Type getType() {
        return Type.INT;
    }

    /**
     * Returns the cached wrapper at the supplied value's minimum-relative index. A
     * {@code null} value fails unboxing, and values outside the configured cache
     * bounds throw an array bounds exception rather than returning a fallback.
     */
    @Override
    public IntPropertyValue createValue(Integer value) {
        return cachedValues[value - min];
    }

    /**
     * Converts a number through {@code intValue()} or parses a decimal integer
     * string before selecting a cached wrapper. Unsupported inputs and malformed
     * strings throw {@code IllegalArgumentException}; out-of-range results throw an
     * array bounds exception.
     */
    @Override
    public IntPropertyValue tryCreateValue(Object value) {
        if (value instanceof Number number) {
            // Numeric coercion uses intValue, so fractional parts do not reach the
            // integer domain.
            return cachedValues[number.intValue() - min];
        } else if (value instanceof String string) {
            int intValue;
            try {
                intValue = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                // Report malformed numeric text through the same argument-error
                // category used for unsupported input types rather than exposing
                // the parser-specific exception.
                throw new IllegalArgumentException("Invalid value for int property type: " + value);
            }
            return cachedValues[intValue - min];
        }
        throw new IllegalArgumentException("Invalid value for int property type: " + value);
    }

    /**
     * {@code IntPropertyValue} represents an integer belonging to its enclosing
     * property domain.
     */
    public final class IntPropertyValue extends BlockPropertyValue<Integer, IntPropertyType, Integer> {

        /**
         * Creates an integer wrapper retaining the enclosing property and supplied
         * raw value.
         */
        IntPropertyValue(Integer value) {
            super(IntPropertyType.this, value);
        }

        /**
         * Returns the raw value minus the property minimum for packed state lookup.
         */
        @Override
        public int getIndex() {
            return value - min;
        }

        /**
         * Returns the raw integer retained for NBT serialization and state hashing.
         */
        @Override
        public Integer getSerializedValue() {
            return value;
        }

        /**
         * Returns a diagnostic description containing this integer property's name
         * and raw value.
         */
        @Override
        public String toString() {
            return "IntPropertyValue(name=" + name + ", value=" + value + ")";
        }
    }
}
