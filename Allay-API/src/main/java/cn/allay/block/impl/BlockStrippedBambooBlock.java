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
public interface BlockStrippedBambooBlock extends Block {
    BlockType<BlockStrippedBambooBlock> TYPE = BlockTypeBuilder
            .builder(BlockStrippedBambooBlock.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
