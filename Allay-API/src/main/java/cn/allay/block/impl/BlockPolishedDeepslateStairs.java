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
public interface BlockPolishedDeepslateStairs extends Block {
    BlockType<BlockPolishedDeepslateStairs> TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateStairs.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS)
            .property(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT,
                    VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
}
