package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLapisBlock extends Block {
    BlockType<BlockLapisBlock> TYPE = BlockTypeBuilder
            .builder(BlockLapisBlock.class)
            .vanillaBlock(VanillaBlockId.LAPIS_BLOCK, true)
            .addBasicComponents()
            .build();
}
