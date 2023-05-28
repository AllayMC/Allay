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
public interface BlockNetherBrickFence extends Block {
    BlockType<BlockNetherBrickFence> TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickFence.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
