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
public interface BlockCrimsonWallSign extends Block {
    BlockType<BlockCrimsonWallSign> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonWallSign.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
