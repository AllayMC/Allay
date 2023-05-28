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
public interface BlockCauldron extends Block {
    BlockType<BlockCauldron> TYPE = BlockTypeBuilder
            .builder(BlockCauldron.class)
            .vanillaBlock(VanillaBlockId.CAULDRON, true)
            .withProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID,
                    VanillaBlockPropertyTypes.FILL_LEVEL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
