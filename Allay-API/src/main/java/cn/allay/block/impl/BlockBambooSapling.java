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
public interface BlockBambooSapling extends Block {
    BlockType<BlockBambooSapling> TYPE = BlockTypeBuilder
            .builder(BlockBambooSapling.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT,
                    VanillaBlockPropertyTypes.SAPLING_TYPE)
            .addBasicComponents()
            .build();
}
