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
public interface BlockBambooStandingSign extends Block {
    BlockType<BlockBambooStandingSign> TYPE = BlockTypeBuilder
            .builder(BlockBambooStandingSign.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_STANDING_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .addBasicComponents()
            .build();
}
