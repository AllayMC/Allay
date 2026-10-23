package org.allaymc.api.block.property.type;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * {@code BaseBlockPropertyType} stores the name, accepted values, default value,
 * and packed-index width shared by block property domains. Subclasses define value
 * conversion and serialization; this base retains the supplied list and only
 * rejects a {@code null} default rather than validating domain membership.
 *
 * @author daoge_cmd
 */
@Getter
@ToString
public abstract sealed class BaseBlockPropertyType<DATATYPE> implements BlockPropertyType<DATATYPE> permits BooleanPropertyType, EnumPropertyType, IntPropertyType {

    /**
     * Serialized block property name.
     */
    protected final String name;
    /**
     * {@code validValues} retains the constructor's list and is exposed by the
     * generated getter. Callers must preserve its entries because subclass caches
     * and bit widths are prepared separately.
     */
    protected final List<DATATYPE> validValues;
    /**
     * Default raw property value.
     */
    protected final DATATYPE defaultValue;
    /**
     * Packed value index width, bits.
     */
    protected final byte bitSize;

    /**
     * Stores the supplied domain metadata and retains {@code validValues} without
     * copying it. {@code defaultValue} must be non-{@code null}, but this
     * constructor does not verify that it appears in the valid-value list.
     */
    protected BaseBlockPropertyType(String name, List<DATATYPE> validValues, DATATYPE defaultValue, byte bitSize) {
        this.name = name;
        this.validValues = validValues;
        this.defaultValue = Objects.requireNonNull(defaultValue);
        this.bitSize = bitSize;
    }
}
