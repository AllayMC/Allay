package cn.allay.block.component.property;

import cn.allay.block.component.BlockComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockProperty<DATATYPE> extends BlockComponentImpl permits BaseBlockProperty {
    String getName();
    void set(DATATYPE value);
    DATATYPE get();
}
