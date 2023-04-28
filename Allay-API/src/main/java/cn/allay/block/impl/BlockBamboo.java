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
public interface BlockBamboo extends Block {
    BlockType<BlockBamboo> TYPE = BlockTypeBuilder
            .builder(BlockBamboo.class)
            .vanillaBlock(VanillaBlockId.BAMBOO, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT,
                    VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE,
                    VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
            .addBasicComponents()
            .build();
}
