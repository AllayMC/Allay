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
public interface BlockSapling extends Block {
    BlockType<BlockSapling> TYPE = BlockTypeBuilder
            .builder(BlockSapling.class)
            .vanillaBlock(VanillaBlockId.SAPLING, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT,
                    VanillaBlockPropertyTypes.SAPLING_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
