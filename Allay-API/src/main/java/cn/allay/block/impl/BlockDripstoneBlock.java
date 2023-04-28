package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDripstoneBlock extends Block {
    BlockType<BlockDripstoneBlock> TYPE = BlockTypeBuilder
            .builder(BlockDripstoneBlock.class)
            .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK, true)
            .addBasicComponents()
            .build();
}
