package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRawIronBlock extends Block {
    BlockType<BlockRawIronBlock> TYPE = BlockTypeBuilder
            .builder(BlockRawIronBlock.class)
            .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK, true)
            .addBasicComponents()
            .build();
}
