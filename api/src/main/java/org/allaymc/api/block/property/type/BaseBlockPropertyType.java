package org.allaymc.api.block.property.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.allaymc.api.block.property.BlockPropertyProcessor;

import java.util.List;
import java.util.Objects;

/**
 * The base class for block property type (boolean, enum, int)
 *
 * @author daoge_cmd
 */
@Getter
@ToString
public abstract sealed class BaseBlockPropertyType<DATATYPE> implements BlockPropertyType<DATATYPE> permits BooleanPropertyType, EnumPropertyType, IntPropertyType {

    protected final String name;
    protected final List<DATATYPE> validValues;
    protected final DATATYPE defaultValue;
    protected final byte bitSize;

    @Setter
    protected BlockPropertyProcessor processor;

    protected BaseBlockPropertyType(String name, List<DATATYPE> validValues, DATATYPE defaultValue, byte bitSize) {
        this.name = name;
        this.validValues = validValues;
        this.defaultValue = Objects.requireNonNull(defaultValue);
        this.bitSize = bitSize;
    }
}
