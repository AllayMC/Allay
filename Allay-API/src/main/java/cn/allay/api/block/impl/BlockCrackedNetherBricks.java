package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedNetherBricks extends Block {
    BlockType<BlockCrackedNetherBricks> TYPE = BlockTypeBuilder
            .builder(BlockCrackedNetherBricks.class)
            .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
