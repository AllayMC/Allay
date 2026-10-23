package org.allaymc.api.entity.property.type;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * {@code BaseEntityPropertyType} stores the name, value list, and default
 * shared by concrete entity property definitions. The constructor retains the
 * supplied list directly and rejects a {@code null} default, while each
 * concrete type decides how its list represents allowed values or range
 * endpoints.
 *
 * @author daoge_cmd
 */
@Getter
@ToString
public abstract sealed class BaseEntityPropertyType<DATATYPE> implements EntityPropertyType<DATATYPE>
        permits BooleanPropertyType, IntPropertyType, FloatPropertyType, EnumPropertyType {

    /**
     * Property name used in entity definitions and serialized compounds.
     */
    protected final String name;
    /**
     * {@code validValues} retains the supplied definition list without copying
     * it. The generated getter exposes the same list, including any mutability
     * provided by the concrete property's constructor.
     */
    protected final List<DATATYPE> validValues;
    /**
     * Nonnull fallback used when the entity has no stored property value.
     */
    protected final DATATYPE defaultValue;

    /**
     * Creates common property metadata retaining the name and value list as
     * supplied. The default must be nonnull, but this constructor does not
     * check whether the list contains it or validate the property name.
     *
     * @throws NullPointerException if {@code defaultValue} is {@code null}
     */
    protected BaseEntityPropertyType(String name, List<DATATYPE> validValues, DATATYPE defaultValue) {
        this.name = name;
        this.validValues = validValues;
        this.defaultValue = Objects.requireNonNull(defaultValue);
    }
}
