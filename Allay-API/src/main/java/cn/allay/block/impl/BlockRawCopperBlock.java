package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRawCopperBlock extends Block {
    BlockType<BlockRawCopperBlock> TYPE = BlockTypeBuilder
            .builder(BlockRawCopperBlock.class)
            .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK, true)
            .addBasicComponents()
            .build();
}
