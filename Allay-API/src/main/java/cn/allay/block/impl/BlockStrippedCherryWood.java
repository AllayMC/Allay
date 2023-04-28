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
public interface BlockStrippedCherryWood extends Block {
    BlockType<BlockStrippedCherryWood> TYPE = BlockTypeBuilder
            .builder(BlockStrippedCherryWood.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
