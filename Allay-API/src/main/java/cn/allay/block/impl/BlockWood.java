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
public interface BlockWood extends Block {
    BlockType<BlockWood> TYPE = BlockTypeBuilder
            .builder(BlockWood.class)
            .vanillaBlock(VanillaBlockId.WOOD, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS,
                    VanillaBlockPropertyTypes.STRIPPED_BIT,
                    VanillaBlockPropertyTypes.WOOD_TYPE)
            .addBasicComponents()
            .build();
}
