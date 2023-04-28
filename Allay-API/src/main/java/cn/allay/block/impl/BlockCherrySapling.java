package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherrySapling extends Block {
    BlockType<BlockCherrySapling> TYPE = BlockTypeBuilder
            .builder(BlockCherrySapling.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SAPLING, true)
            .withProperties(VanillaBlockPropertyTypes.AGE_BIT)
            .addBasicComponents()
            .build();
}
