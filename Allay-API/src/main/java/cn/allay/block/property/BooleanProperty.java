package cn.allay.block.property;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public final class BooleanProperty extends BaseBlockProperty<Boolean> {
    public BooleanProperty(String name) {
        super(name);
    }

    public BooleanProperty(String name, Boolean defaultData) {
        super(name, defaultData);
    }
}
