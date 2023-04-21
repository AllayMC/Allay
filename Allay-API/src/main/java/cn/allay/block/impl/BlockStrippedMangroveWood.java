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
public interface BlockStrippedMangroveWood extends Block {
    BlockType<BlockStrippedMangroveWood> TYPE = BlockTypeBuilder
            .builder(BlockStrippedMangroveWood.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
