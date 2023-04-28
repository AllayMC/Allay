package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledNetherBricks extends Block {
    BlockType<BlockChiseledNetherBricks> TYPE = BlockTypeBuilder
            .builder(BlockChiseledNetherBricks.class)
            .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS, true)
            .addBasicComponents()
            .build();
}
