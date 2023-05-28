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
public interface BlockYellowFlower extends Block {
    BlockType<BlockYellowFlower> TYPE = BlockTypeBuilder
            .builder(BlockYellowFlower.class)
            .vanillaBlock(VanillaBlockId.YELLOW_FLOWER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
