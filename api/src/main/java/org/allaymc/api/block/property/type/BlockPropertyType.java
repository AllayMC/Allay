package org.allaymc.api.block.property.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * {@code BlockPropertyType} describes a named boolean, integer, or enum domain used
 * by block states. Property values pair that domain with a raw value, an index for
 * packed state lookup, and a serialized representation for NBT and block-state
 * hashing.
 *
 * @author daoge_cmd
 */
public sealed interface BlockPropertyType<DATATYPE> permits BaseBlockPropertyType {

    /**
     * Returns the storage category for one of the three concrete property classes.
     * Any other class, including {@code null}, returns {@code null}; this method
     * compares classes exactly rather than inspecting assignability.
     */
    static Type getPropertyType(Class<?> clazz) {
        if (clazz == BooleanPropertyType.class) return Type.BOOLEAN;
        else if (clazz == IntPropertyType.class) return Type.INT;
        else if (clazz == EnumPropertyType.class) return Type.ENUM;
        else return null;
    }

    /**
     * Returns the serialized name identifying this property within a block type.
     */
    String getName();

    /**
     * Returns the raw value used when constructing the property's default wrapper.
     */
    DATATYPE getDefaultValue();

    /**
     * Returns the property domain values prepared by the implementation. Callers
     * must preserve their contents because wrapper caches and packed-index widths
     * may already depend on the originally supplied domain.
     */
    List<DATATYPE> getValidValues();

    /**
     * Returns the boolean, integer, or enum category of this property.
     */
    Type getType();

    /**
     * Returns a wrapper for a raw value from this property's domain. Callers must
     * provide a supported non-{@code null} value; concrete implementations use
     * cached lookup rather than a uniform validation or failure policy.
     */
    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createValue(DATATYPE value);

    /**
     * Converts a supported external representation into this property's cached
     * value wrapper. Accepted representations and range failures depend on the
     * concrete type; unsupported input categories produce {@code
     * IllegalArgumentException} rather than a default value.
     */
    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> tryCreateValue(Object value);

    /**
     * Returns the wrapper produced by converting this property's configured default
     * value.
     */
    default BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createDefaultValue() {
        return createValue(getDefaultValue());
    }

    /**
     * Returns the bit width reserved for this property's index in packed state
     * keys.
     */
    byte getBitSize();

    /**
     * {@code Type} identifies the raw domain category of a block property.
     */
    @Getter
    enum Type {
        /**
         * Boolean truth value domain.
         */
        BOOLEAN,
        /**
         * Contiguous integer value domain.
         */
        INT,
        /**
         * Named enumeration value domain.
         */
        ENUM
    }

    /**
     * {@code BlockPropertyValue} pairs a property domain with its raw value and
     * exposes the derived index and serialized value. Concrete subclasses provide
     * the encodings used for packed lookup and NBT; the generated constructor
     * retains both supplied references.
     */
    @Getter
    @AllArgsConstructor
    abstract sealed class BlockPropertyValue<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>, SERIALIZED_DATATYPE>
            permits BooleanPropertyType.BooleanPropertyValue, EnumPropertyType.EnumPropertyValue, IntPropertyType.IntPropertyValue {

        /**
         * Owning property domain reference.
         */
        protected final PROPERTY propertyType;
        /**
         * Unserialized property domain value.
         */
        protected final DATATYPE value;

        /**
         * Returns the value index used when packing this property into a state key.
         */
        public abstract int getIndex();

        /**
         * Returns the representation written to NBT and used by state hashing.
         * Boolean values serialize as bytes, enum values as lowercase names, and
         * integer values retain their numeric representation.
         */
        public abstract SERIALIZED_DATATYPE getSerializedValue();

        /**
         * Returns a diagnostic property-name and serialized-value pair separated by
         * an equals sign.
         */
        @Override
        public String toString() {
            return propertyType.getName() + "=" + getSerializedValue();
        }
    }
}
