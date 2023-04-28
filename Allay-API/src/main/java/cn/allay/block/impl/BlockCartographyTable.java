package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCartographyTable extends Block {
    BlockType<BlockCartographyTable> TYPE = BlockTypeBuilder
            .builder(BlockCartographyTable.class)
            .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE, true)
            .addBasicComponents()
            .build();
}
