package cn.allay.block.property.type;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockProperty<DATATYPE> permits BaseBlockProperty {
    String getName();
    DATATYPE get();
}
