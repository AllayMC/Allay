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
public interface BlockChiseledNetherBricks extends Block {
    BlockType<BlockChiseledNetherBricks> TYPE = BlockTypeBuilder
            .builder(BlockChiseledNetherBricks.class)
            .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
