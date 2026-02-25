package org.allaymc.api.entity.property.type;

import java.util.List;

/**
 * Represents a type of entity property.
 *
 * @author daoge_cmd
 */
public sealed interface EntityPropertyType<DATATYPE> permits BaseEntityPropertyType {

    /**
     * Get the name of this property type.
     *
     * @return the name
     */
    String getName();

    /**
     * Get the default value of this property type.
     *
     * @return the default value
     */
    DATATYPE getDefaultValue();

    /**
     * Get the valid values of this property type.
     *
     * @return the valid values
     */
    List<DATATYPE> getValidValues();

    /**
     * Get the type of this property type.
     *
     * @return the type
     */
    Type getType();

    /**
     * The type of entity property.
     */
    enum Type {
        INT,
        FLOAT,
        BOOLEAN,
        ENUM
    }
}
