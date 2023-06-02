package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
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
