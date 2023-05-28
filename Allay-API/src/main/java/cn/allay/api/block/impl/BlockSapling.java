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
public interface BlockSapling extends Block {
    BlockType<BlockSapling> TYPE = BlockTypeBuilder
            .builder(BlockSapling.class)
            .vanillaBlock(VanillaBlockId.SAPLING, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT,
                    VanillaBlockPropertyTypes.SAPLING_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
