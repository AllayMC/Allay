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
public interface BlockCobblestoneWall extends Block {
    BlockType<BlockCobblestoneWall> TYPE = BlockTypeBuilder
            .builder(BlockCobblestoneWall.class)
            .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL, true)
            .withProperties(VanillaBlockPropertyTypes.WALL_BLOCK_TYPE,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST,
                    VanillaBlockPropertyTypes.WALL_POST_BIT)
            .addBasicComponents()
            .build();
}
