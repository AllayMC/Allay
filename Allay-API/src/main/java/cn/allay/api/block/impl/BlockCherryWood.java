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
public interface BlockCherryWood extends Block {
    BlockType<BlockCherryWood> TYPE = BlockTypeBuilder
            .builder(BlockCherryWood.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WOOD, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS,
                    VanillaBlockPropertyTypes.STRIPPED_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
