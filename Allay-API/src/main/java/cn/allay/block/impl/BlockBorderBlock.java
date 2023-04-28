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
public interface BlockBorderBlock extends Block {
    BlockType<BlockBorderBlock> TYPE = BlockTypeBuilder
            .builder(BlockBorderBlock.class)
            .vanillaBlock(VanillaBlockId.BORDER_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH,
                    VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST,
                    VanillaBlockPropertyTypes.WALL_POST_BIT)
            .addBasicComponents()
            .build();
}
