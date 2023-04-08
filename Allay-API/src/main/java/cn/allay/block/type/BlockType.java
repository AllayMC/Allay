package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.property.type.BlockPropertyType;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockType<T extends Block> {
    List<BlockPropertyType<?>> getBlockPropertyList();
}
