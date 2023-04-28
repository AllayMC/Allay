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
public interface BlockDarkoakStandingSign extends Block {
    BlockType<BlockDarkoakStandingSign> TYPE = BlockTypeBuilder
            .builder(BlockDarkoakStandingSign.class)
            .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .addBasicComponents()
            .build();
}
