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
public interface BlockLavaCauldron extends Block {
    BlockType<BlockLavaCauldron> TYPE = BlockTypeBuilder
            .builder(BlockLavaCauldron.class)
            .vanillaBlock(VanillaBlockId.LAVA_CAULDRON, true)
            .withProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID,
                    VanillaBlockPropertyTypes.FILL_LEVEL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
