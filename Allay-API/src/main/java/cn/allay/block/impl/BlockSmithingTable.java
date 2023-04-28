package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmithingTable extends Block {
    BlockType<BlockSmithingTable> TYPE = BlockTypeBuilder
            .builder(BlockSmithingTable.class)
            .vanillaBlock(VanillaBlockId.SMITHING_TABLE, true)
            .addBasicComponents()
            .build();
}
