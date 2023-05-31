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
public interface BlockLavaCauldron extends Block {
    BlockType<BlockLavaCauldron> TYPE = BlockTypeBuilder
            .builder(BlockLavaCauldron.class)
            .vanillaBlock(VanillaBlockId.LAVA_CAULDRON, true)
            .withProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID,
                    VanillaBlockPropertyTypes.FILL_LEVEL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
