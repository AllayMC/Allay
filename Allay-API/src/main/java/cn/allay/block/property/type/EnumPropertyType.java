package cn.allay.block.property.type;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class EnumPropertyType<T extends Enum<T>> extends BaseBlockPropertyType<T> {
    private EnumPropertyType(String name, List<T> validValues, T defaultData) {
        super(name, validValues, defaultData);
    }

    public static <T extends Enum<T>> EnumPropertyType<T> createType(String name, List<T> validValues, T defaultData) {
        return new EnumPropertyType<>(name, validValues, defaultData);
    }
}
