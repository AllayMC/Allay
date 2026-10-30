package org.allaymc.api.entity.property.type;

import java.util.List;

/**
 * {@code EntityPropertyType} describes a named entity property, its default,
 * and the representation used by persistence and client synchronization.
 * Boolean and enum properties enumerate choices, integer properties enumerate
 * a range, and {@code float} properties expose only their endpoint values through
 * {@code getValidValues}.
 *
 * @author daoge_cmd
 */
public sealed interface EntityPropertyType<DATATYPE> permits BaseEntityPropertyType {

    /**
     * Returns the property name used by entity definitions and persisted
     * values.
     */
    String getName();

    /**
     * Returns the nonnull value used when this property has no stored value.
     */
    DATATYPE getDefaultValue();

    /**
     * Returns the implementation's property value list without making a
     * defensive copy. Float properties list only their endpoints; enum
     * properties use an array-backed list whose entries can be replaced, so
     * callers must preserve definition order.
     */
    List<DATATYPE> getValidValues();

    /**
     * Returns the representation category used for this entity property
     * definition.
     */
    Type getType();

    /**
     * {@code Type} identifies the wire category of an entity property. The
     * packet encoder serializes these constants by ordinal, so their
     * declaration order determines the client-facing type codes.
     */
    enum Type {
        /**
         * Integer property represented by an inclusive numeric range.
         */
        INT,
        /**
         * Floating-point property represented by two numeric endpoints.
         */
        FLOAT,
        /**
         * Boolean property with {@code true} and {@code false} choices.
         */
        BOOLEAN,
        /**
         * Enum property represented by ordered serialized enum names.
         */
        ENUM
    }
}
