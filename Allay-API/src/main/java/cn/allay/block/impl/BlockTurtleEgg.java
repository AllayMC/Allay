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
public interface BlockTurtleEgg extends Block {
    BlockType<BlockTurtleEgg> TYPE = BlockTypeBuilder
            .builder(BlockTurtleEgg.class)
            .vanillaBlock(VanillaBlockId.TURTLE_EGG, true)
            .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE,
                    VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
