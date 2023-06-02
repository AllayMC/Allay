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
public interface BlockSponge extends Block {
    BlockType<BlockSponge> TYPE = BlockTypeBuilder
            .builder(BlockSponge.class)
            .vanillaBlock(VanillaBlockId.SPONGE, true)
            .withProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
            .addBasicComponents()
            .build();
}
