package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedNetherBricks extends Block {
    BlockType<BlockCrackedNetherBricks> TYPE = BlockTypeBuilder
            .builder(BlockCrackedNetherBricks.class)
            .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS)
            .build();
}
