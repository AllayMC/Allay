package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmithingTable extends Block {
    BlockType<BlockSmithingTable> TYPE = BlockTypeBuilder
            .builder(BlockSmithingTable.class)
            .vanillaBlock(VanillaBlockId.SMITHING_TABLE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
