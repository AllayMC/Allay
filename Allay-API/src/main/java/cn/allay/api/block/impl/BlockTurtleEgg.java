package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTurtleEgg extends Block {
    BlockType<BlockTurtleEgg> TYPE = BlockTypeBuilder
            .builder(BlockTurtleEgg.class)
            .vanillaBlock(VanillaBlockId.TURTLE_EGG, true)
            .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE,
                    VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
