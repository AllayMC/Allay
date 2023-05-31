package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

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
