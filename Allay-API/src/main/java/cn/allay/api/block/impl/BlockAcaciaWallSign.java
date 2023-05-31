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
public interface BlockAcaciaWallSign extends Block {
    BlockType<BlockAcaciaWallSign> TYPE = BlockTypeBuilder
            .builder(BlockAcaciaWallSign.class)
            .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
