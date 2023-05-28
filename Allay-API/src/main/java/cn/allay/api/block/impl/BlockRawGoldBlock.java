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
public interface BlockRawGoldBlock extends Block {
    BlockType<BlockRawGoldBlock> TYPE = BlockTypeBuilder
            .builder(BlockRawGoldBlock.class)
            .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
