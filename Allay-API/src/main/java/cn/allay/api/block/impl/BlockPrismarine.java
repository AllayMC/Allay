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
public interface BlockPrismarine extends Block {
    BlockType<BlockPrismarine> TYPE = BlockTypeBuilder
            .builder(BlockPrismarine.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE, true)
            .withProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
            .addBasicComponents()
            .build();
}
