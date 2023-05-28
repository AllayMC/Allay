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
public interface BlockFlowerPot extends Block {
    BlockType<BlockFlowerPot> TYPE = BlockTypeBuilder
            .builder(BlockFlowerPot.class)
            .vanillaBlock(VanillaBlockId.FLOWER_POT, true)
            .withProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
