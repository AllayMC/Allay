package org.allaymc.api.block.property.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.allaymc.api.block.property.BlockPropertyProcessor;

import java.util.List;

/**
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public sealed interface BlockPropertyType<DATATYPE> permits BaseBlockPropertyType {

    /**
     * Get the property type by the class
     */
    static Type getPropertyType(Class<?> clazz) {
        if (clazz == BooleanPropertyType.class) return Type.BOOLEAN;
        else if (clazz == IntPropertyType.class) return Type.INT;
        else if (clazz == EnumPropertyType.class) return Type.ENUM;
        else return null;
    }

    /**
     * Get the name of this property type
     *
     * @return the name
     */
    String getName();

    /**
     * Get the default value of this property type
     *
     * @return the default value
     */
    DATATYPE getDefaultValue();

    /**
     * Get the valid values of this property type
     *
     * @return the valid values
     */
    List<DATATYPE> getValidValues();

    /**
     * Get the type of this property type
     *
     * @return the type
     */
    Type getType();

    /**
     * Create a new value for this property type
     *
     * @param value the value
     * @return the value
     */
    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createValue(DATATYPE value);

    /**
     * Try to create a new value for this property type
     *
     * @param value the value
     * @return the value
     * @throws IllegalArgumentException if the value is invalid, such as pass a boolean value to an int property type
     */
    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> tryCreateValue(Object value);

    /**
     * Create a default value for this property type
     *
     * @return the value
     */
    default BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createDefaultValue() {
        return createValue(getDefaultValue());
    }

    /**
     * Get the processor for this property type
     *
     * @return the processor, null if not present
     */
    BlockPropertyProcessor getProcessor();

    /**
     * Set the processor for this property type
     *
     * @param processor the processor, or null to remove
     */
    void setProcessor(BlockPropertyProcessor processor);

    /**
     * Get the bit size of this property type
     * <p>
     * Bit size is used to calculate the special value for a block state
     *
     * @return the bit size
     */
    byte getBitSize();

    /**
     * The type of block property
     */
    @Getter
    enum Type {
        BOOLEAN,
        INT,
        ENUM
    }

    /**
     * The base class for block property value (boolean, enum, int).
     */
    @Getter
    @AllArgsConstructor
    abstract sealed class BlockPropertyValue<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>, SERIALIZED_DATATYPE> permits BooleanPropertyType.BooleanPropertyValue, EnumPropertyType.EnumPropertyValue, IntPropertyType.IntPropertyValue {

        /**
         * The property type of this value
         */
        protected final PROPERTY propertyType;
        /**
         * The value of this property
         */
        protected final DATATYPE value;

        public abstract int getIndex();

        /**
         * Get the serialized value of this property.
         * <p>
         * Different from the value, the serialized value is the value that will be stored in the block state nbt.
         * <br>
         * For example, the value of a boolean property is a boolean, but the serialized value is a byte.
         *
         * @return the serialized value
         */
        public abstract SERIALIZED_DATATYPE getSerializedValue();
    }
}
