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
public interface BlockSpruceStandingSign extends Block {
    BlockType<BlockSpruceStandingSign> TYPE = BlockTypeBuilder
            .builder(BlockSpruceStandingSign.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .addBasicComponents()
            .build();
}
