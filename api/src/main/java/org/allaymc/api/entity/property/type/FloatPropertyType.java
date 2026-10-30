package org.allaymc.api.entity.property.type;

import lombok.Getter;

import java.util.List;

/**
 * {@code FloatPropertyType} describes a named floating-point entity property
 * for default lookup, persistence, and client synchronization. The stored
 * value list contains only the minimum and maximum endpoints; the constructor
 * records those endpoints and the default without checking range order or
 * default membership.
 *
 * @author daoge_cmd
 */
@Getter
public final class FloatPropertyType extends BaseEntityPropertyType<Float> {

    /**
     * Configured lower endpoint for this numeric property.
     */
    private final float min;
    /**
     * Configured upper endpoint for this numeric property.
     */
    private final float max;

    /**
     * Creates a {@code float} property retaining the supplied bounds and default. The
     * value list contains the two endpoints only; reversed bounds, nonfinite
     * values, and a default outside the bounds are not rejected.
     */
    private FloatPropertyType(String name, float min, float max, float defaultValue) {
        super(name, List.of(min, max), defaultValue);
        this.min = min;
        this.max = max;
    }

    /**
     * Creates a {@code float} property definition with the supplied bounds and
     * default. Callers choose consistent finite bounds and a suitable default;
     * this factory passes the values through without range validation.
     */
    public static FloatPropertyType of(String name, float min, float max, float defaultValue) {
        return new FloatPropertyType(name, min, max, defaultValue);
    }

    /**
     * Returns the floating-point representation category for this property
     * definition.
     */
    @Override
    public Type getType() {
        return Type.FLOAT;
    }
}
