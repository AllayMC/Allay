package cn.allay.block.property.type;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class BooleanPropertyType extends BaseBlockPropertyType<Boolean> {

    private final BlockPropertyValue<Boolean, BooleanPropertyType> FALSE = new BlockPropertyValue<>(this, false);
    private final BlockPropertyValue<Boolean, BooleanPropertyType> TRUE = new BlockPropertyValue<>(this, true);

    private BooleanPropertyType(String name, Boolean defaultData) {
        super(name, List.of(true, false), defaultData);
    }

    public static BooleanPropertyType createType(String name, Boolean defaultData) {
        return new BooleanPropertyType(name, defaultData);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public BlockPropertyValue<Boolean, ? extends BlockPropertyType<Boolean>> createValue(Boolean value) {
        return value ? TRUE : FALSE;
    }

    @Override
    public BlockPropertyValue<Boolean, BooleanPropertyType> tryCreateValue(Object value) {
        if (value instanceof Boolean bool) {
            return bool ? TRUE : FALSE;
        } else if (value instanceof Number number) {
            var intValue = number.intValue();
            if (intValue == 0 || intValue == 1)
                return intValue == 1 ? TRUE : FALSE;
        }
        throw new IllegalArgumentException("Invalid value for boolean property type: " + value);
    }
}
