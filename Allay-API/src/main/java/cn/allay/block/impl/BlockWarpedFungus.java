package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedFungus extends Block {
    BlockType<BlockWarpedFungus> TYPE = BlockTypeBuilder
            .builder(BlockWarpedFungus.class)
            .vanillaBlock(VanillaBlockId.WARPED_FUNGUS, true)
            .addBasicComponents()
            .build();
}
