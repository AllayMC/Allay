package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCraftingTable extends Block {
    BlockType<BlockCraftingTable> TYPE = BlockTypeBuilder
            .builder(BlockCraftingTable.class)
            .vanillaBlock(VanillaBlockId.CRAFTING_TABLE, true)
            .addBasicComponents()
            .build();
}
