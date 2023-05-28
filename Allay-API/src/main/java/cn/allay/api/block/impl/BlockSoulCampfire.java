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
public interface BlockSoulCampfire extends Block {
    BlockType<BlockSoulCampfire> TYPE = BlockTypeBuilder
            .builder(BlockSoulCampfire.class)
            .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.EXTINGUISHED)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
