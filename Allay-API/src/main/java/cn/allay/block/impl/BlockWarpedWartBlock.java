package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedWartBlock extends Block {
    BlockType<BlockWarpedWartBlock> TYPE = BlockTypeBuilder
            .builder(BlockWarpedWartBlock.class)
            .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK, true)
            .addBasicComponents()
            .build();
}
