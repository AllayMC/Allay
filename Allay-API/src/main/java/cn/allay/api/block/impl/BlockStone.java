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
public interface BlockStone extends Block {
    BlockType<BlockStone> TYPE = BlockTypeBuilder
            .builder(BlockStone.class)
            .vanillaBlock(VanillaBlockId.STONE, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_TYPE)
            .addBasicComponents()
            .build();
}
