package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIronBlock extends Block {
    BlockType<BlockIronBlock> TYPE = BlockTypeBuilder
            .builder(BlockIronBlock.class)
            .vanillaBlock(VanillaBlockId.IRON_BLOCK, true)
            .addBasicComponents()
            .build();
}
