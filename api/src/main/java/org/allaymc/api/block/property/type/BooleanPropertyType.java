package org.allaymc.api.block.property.type;

import lombok.Getter;

import java.util.List;

/**
 * {@code BooleanPropertyType} describes a two-valued block property and caches one
 * wrapper for each truth value. The wrappers use {@code false}-zero and {@code true}-one indices
 * and byte encodings, while {@code getValidValues} lists {@code true} before {@code
 * false} for domain enumeration.
 *
 * @author daoge_cmd
 */
public final class BooleanPropertyType extends BaseBlockPropertyType<Boolean> {

    // FALSE and TRUE belong to this property instance. Every successful conversion
    // reuses one of these wrappers, keeping its domain identity attached to the
    // value.
    /**
     * Cached {@code false} value wrapper.
     */
    private final BooleanPropertyValue FALSE = new BooleanPropertyValue(false);
    /**
     * Cached {@code true} value wrapper.
     */
    private final BooleanPropertyValue TRUE = new BooleanPropertyValue(true);

    /**
     * Creates a named boolean domain with a non-{@code null} default and one-bit
     * indices.
     */
    private BooleanPropertyType(String name, Boolean defaultData) {
        super(name, List.of(true, false), defaultData, (byte) 1);
    }

    /**
     * Creates a distinct boolean property domain with the supplied name and
     * non-{@code null} default.
     */
    public static BooleanPropertyType of(String name, Boolean defaultData) {
        return new BooleanPropertyType(name, defaultData);
    }

    /**
     * Returns the boolean storage category used by this property implementation.
     */
    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    /**
     * Returns this property's cached wrapper for the supplied truth value. The
     * input must be non-{@code null} because this lookup unboxes it; repeated calls
     * for one value return the same wrapper.
     */
    @Override
    public BooleanPropertyValue createValue(Boolean value) {
        return value ? TRUE : FALSE;
    }

    /**
     * Converts booleans, numbers whose {@code intValue()} is {@code 0} or {@code
     * 1}, and case-insensitive {@code "true"} or {@code "false"} strings into
     * cached wrappers. Other inputs, including {@code null}, throw {@code
     * IllegalArgumentException}; numeric conversion follows {@code
     * Number.intValue()} semantics.
     */
    @Override
    public BooleanPropertyValue tryCreateValue(Object value) {
        if (value instanceof Boolean bool) {
            return bool ? TRUE : FALSE;
        } else if (value instanceof Number number) {
            // Numeric coercion follows intValue, so fractional inputs can become
            // accepted bits.
            var intValue = number.intValue();
            if (intValue == 0 || intValue == 1) {
                return intValue == 1 ? TRUE : FALSE;
            }
        } else if (value instanceof String string) {
            if (string.equalsIgnoreCase("true")) return TRUE;
            if (string.equalsIgnoreCase("false")) return FALSE;
        }
        throw new IllegalArgumentException("Invalid value for boolean property type: " + value);
    }

    /**
     * {@code BooleanPropertyValue} represents a cached truth value belonging to its
     * enclosing property.
     */
    public final class BooleanPropertyValue extends BlockPropertyValue<Boolean, BooleanPropertyType, Byte> {
        /**
         * False-zero, {@code true}-one packed index.
         */
        @Getter
        private final int index;

        /**
         * Creates a wrapper tied to the enclosing boolean property and derives its
         * numeric index.
         */
        BooleanPropertyValue(Boolean value) {
            super(BooleanPropertyType.this, value);
            this.index = value ? 1 : 0;
        }

        /**
         * Returns the byte encoding {@code 0} for {@code false} or {@code 1} for {@code true}.
         */
        @Override
        public Byte getSerializedValue() {
            // State NBT stores booleans as numeric bytes, preserving the false-zero
            // and true-one encoding used by state hashes.
            return (byte) (value ? 1 : 0);
        }

        /**
         * Returns a diagnostic description containing this boolean property's name
         * and raw value.
         */
        @Override
        public String toString() {
            return "BoolPropertyValue(name=" + name + ", value=" + value + ")";
        }
    }
}
