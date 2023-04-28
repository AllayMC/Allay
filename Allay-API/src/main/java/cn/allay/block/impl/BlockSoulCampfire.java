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
public interface BlockSoulCampfire extends Block {
    BlockType<BlockSoulCampfire> TYPE = BlockTypeBuilder
            .builder(BlockSoulCampfire.class)
            .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.EXTINGUISHED)
            .addBasicComponents()
            .build();
}
