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
public interface BlockBamboo extends Block {
    BlockType<BlockBamboo> TYPE = BlockTypeBuilder
            .builder(BlockBamboo.class)
            .vanillaBlock(VanillaBlockId.BAMBOO, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT,
                    VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE,
                    VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
