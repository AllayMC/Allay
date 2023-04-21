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
public interface BlockDirt extends Block {
    BlockType<BlockDirt> TYPE = BlockTypeBuilder
            .builder(BlockDirt.class)
            .vanillaBlock(VanillaBlockId.DIRT)
            .property(VanillaBlockPropertyTypes.DIRT_TYPE)
            .build();
}
