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
public interface BlockMangroveWallSign extends Block {
    BlockType<BlockMangroveWallSign> TYPE = BlockTypeBuilder
            .builder(BlockMangroveWallSign.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WALL_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
