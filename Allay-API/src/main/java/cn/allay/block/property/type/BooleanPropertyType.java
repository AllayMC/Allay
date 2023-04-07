package cn.allay.block.property.type;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class BooleanPropertyType extends BaseBlockPropertyType<Boolean> {
    private BooleanPropertyType(String name, Boolean defaultData) {
        super(name, List.of(true, false), defaultData);
    }

    public static BooleanPropertyType createType(String name, Boolean defaultData) {
        return new BooleanPropertyType(name, defaultData);
    }
}
