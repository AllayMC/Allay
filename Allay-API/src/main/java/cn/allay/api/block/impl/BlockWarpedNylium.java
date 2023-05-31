package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedNylium extends Block {
    BlockType<BlockWarpedNylium> TYPE = BlockTypeBuilder
            .builder(BlockWarpedNylium.class)
            .vanillaBlock(VanillaBlockId.WARPED_NYLIUM, true)
            .addBasicComponents()
            .build();
}
