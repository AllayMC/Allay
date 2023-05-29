package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCampfire extends Block {
    BlockType<BlockCampfire> TYPE = BlockTypeBuilder
            .builder(BlockCampfire.class)
            .vanillaBlock(VanillaBlockId.CAMPFIRE, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.EXTINGUISHED)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
