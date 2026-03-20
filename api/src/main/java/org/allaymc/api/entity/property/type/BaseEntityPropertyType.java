package org.allaymc.api.entity.property.type;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * The base class for entity property type (boolean, enum, int, float).
 *
 * @author daoge_cmd
 */
@Getter
@ToString
public abstract sealed class BaseEntityPropertyType<DATATYPE> implements EntityPropertyType<DATATYPE>
        permits BooleanPropertyType, IntPropertyType, FloatPropertyType, EnumPropertyType {

    protected final String name;
    protected final List<DATATYPE> validValues;
    protected final DATATYPE defaultValue;

    protected BaseEntityPropertyType(String name, List<DATATYPE> validValues, DATATYPE defaultValue) {
        this.name = name;
        this.validValues = validValues;
        this.defaultValue = Objects.requireNonNull(defaultValue);
    }
}
