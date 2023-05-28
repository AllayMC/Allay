package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

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
            .build().register(BlockTypeRegistry.getRegistry());
}
