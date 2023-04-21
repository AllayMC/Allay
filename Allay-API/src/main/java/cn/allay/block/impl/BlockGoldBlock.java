package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGoldBlock extends Block {
    BlockType<BlockGoldBlock> TYPE = BlockTypeBuilder
            .builder(BlockGoldBlock.class)
            .vanillaBlock(VanillaBlockId.GOLD_BLOCK)
            .build();
}
