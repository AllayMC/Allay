package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

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
