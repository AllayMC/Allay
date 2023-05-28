package cn.allay.api.block.impl;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEnchantingTable extends Block {
    BlockType<BlockEnchantingTable> TYPE = BlockTypeBuilder
            .builder(BlockEnchantingTable.class)
            .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
