package org.allaymc.api.entity.property.type;

import java.util.List;

/**
 * @author daoge_cmd
 */
public final class BooleanPropertyType extends BaseEntityPropertyType<Boolean> {

    private BooleanPropertyType(String name, Boolean defaultValue) {
        super(name, List.of(true, false), defaultValue);
    }

    public static BooleanPropertyType of(String name, boolean defaultValue) {
        return new BooleanPropertyType(name, defaultValue);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
