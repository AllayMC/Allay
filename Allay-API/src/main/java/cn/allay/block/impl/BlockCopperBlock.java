package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperBlock extends Block {
    BlockType<BlockCopperBlock> TYPE = BlockTypeBuilder
            .builder(BlockCopperBlock.class)
            .vanillaBlock(VanillaBlockId.COPPER_BLOCK)
            .build();
}
