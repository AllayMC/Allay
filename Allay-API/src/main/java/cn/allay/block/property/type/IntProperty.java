package cn.allay.block.property.type;

import lombok.Getter;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
@Getter
public final class IntProperty extends BaseBlockProperty<Integer> {

    private final int min;
    private final int max;

    public IntProperty(String name, int min, int max) {
        this(name, 0, min, max);
    }

    public IntProperty(String name, Integer defaultData, int min, int max) {
        super(name, defaultData);
        this.min = min;
        this.max = max;
    }
}
