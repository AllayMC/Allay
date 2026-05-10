package org.allaymc.api.entity.property.type;

import lombok.Getter;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public final class FloatPropertyType extends BaseEntityPropertyType<Float> {

    private final float min;
    private final float max;

    private FloatPropertyType(String name, float min, float max, float defaultValue) {
        super(name, List.of(min, max), defaultValue);
        this.min = min;
        this.max = max;
    }

    public static FloatPropertyType of(String name, float min, float max, float defaultValue) {
        return new FloatPropertyType(name, min, max, defaultValue);
    }

    @Override
    public Type getType() {
        return Type.FLOAT;
    }
}
