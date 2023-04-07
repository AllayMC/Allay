package cn.allay.block.property;

import cn.allay.block.component.BlockComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockProperty<DATATYPE> permits BaseBlockProperty {
    String getName();
    void set(DATATYPE value);
    DATATYPE get();
}
