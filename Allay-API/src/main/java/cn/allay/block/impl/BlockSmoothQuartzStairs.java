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
public interface BlockSmoothQuartzStairs extends Block {
    BlockType<BlockSmoothQuartzStairs> TYPE = BlockTypeBuilder
            .builder(BlockSmoothQuartzStairs.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
            .property(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT,
                    VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .build();
}
