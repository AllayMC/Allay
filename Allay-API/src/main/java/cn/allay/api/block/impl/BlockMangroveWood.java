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
public interface BlockMangroveWood extends Block {
    BlockType<BlockMangroveWood> TYPE = BlockTypeBuilder
            .builder(BlockMangroveWood.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_WOOD, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS,
                    VanillaBlockPropertyTypes.STRIPPED_BIT)
            .addBasicComponents()
            .build();
}
