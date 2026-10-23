package org.allaymc.api.entity.property.type;

import java.util.List;

/**
 * {@code BooleanPropertyType} describes a named boolean entity property for
 * default lookup, persistence, and client synchronization. The value list is
 * the immutable pair {@code true}, {@code false}, and callers choose the
 * default when constructing the definition.
 *
 * @author daoge_cmd
 */
public final class BooleanPropertyType extends BaseEntityPropertyType<Boolean> {

    /**
     * Creates a boolean property definition with both boolean choices
     * available. The superclass rejects a {@code null} default, and the
     * immutable value list preserves its {@code true}, {@code false} order.
     */
    private BooleanPropertyType(String name, Boolean defaultValue) {
        super(name, List.of(true, false), defaultValue);
    }

    /**
     * Creates a named boolean property with the supplied default value.
     */
    public static BooleanPropertyType of(String name, boolean defaultValue) {
        return new BooleanPropertyType(name, defaultValue);
    }

    /**
     * Returns the boolean representation category for this property
     * definition.
     */
    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
