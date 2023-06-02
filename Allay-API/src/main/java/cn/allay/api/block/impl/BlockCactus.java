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
public interface BlockCactus extends Block {
    BlockType<BlockCactus> TYPE = BlockTypeBuilder
            .builder(BlockCactus.class)
            .vanillaBlock(VanillaBlockId.CACTUS, true)
            .withProperties(VanillaBlockPropertyTypes.CACTUS_AGE)
            .addBasicComponents()
            .build();
}
