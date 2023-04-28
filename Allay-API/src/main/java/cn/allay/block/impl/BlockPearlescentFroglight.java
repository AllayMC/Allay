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
public interface BlockPearlescentFroglight extends Block {
    BlockType<BlockPearlescentFroglight> TYPE = BlockTypeBuilder
            .builder(BlockPearlescentFroglight.class)
            .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
