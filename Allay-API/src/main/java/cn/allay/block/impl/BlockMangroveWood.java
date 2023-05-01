package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

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
            .build().register(BlockTypeRegistry.getRegistry());
}
