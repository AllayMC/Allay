package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

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
            .build();
}
