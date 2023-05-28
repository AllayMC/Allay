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
public interface BlockInvisibleBedrock extends Block {
    BlockType<BlockInvisibleBedrock> TYPE = BlockTypeBuilder
            .builder(BlockInvisibleBedrock.class)
            .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
