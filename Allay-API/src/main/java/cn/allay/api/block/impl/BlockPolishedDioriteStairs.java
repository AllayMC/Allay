package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDioriteStairs extends Block {
    BlockType<BlockPolishedDioriteStairs> TYPE = BlockTypeBuilder
            .builder(BlockPolishedDioriteStairs.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS, true)
            .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT,
                    VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addBasicComponents()
            .build();
}
