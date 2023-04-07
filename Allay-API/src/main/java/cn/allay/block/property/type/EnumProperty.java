package cn.allay.block.property.type;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class EnumProperty<T extends Enum<T>> extends BaseBlockProperty<T> {
    public EnumProperty(String name, T defaultData) {
        super(name, defaultData);
    }
}
