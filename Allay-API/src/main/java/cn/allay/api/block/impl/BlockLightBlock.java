package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlock extends Block {
    BlockType<BlockLightBlock> TYPE = BlockTypeBuilder
            .builder(BlockLightBlock.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
