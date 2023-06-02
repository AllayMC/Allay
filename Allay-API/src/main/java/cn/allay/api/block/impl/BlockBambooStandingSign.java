package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
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
