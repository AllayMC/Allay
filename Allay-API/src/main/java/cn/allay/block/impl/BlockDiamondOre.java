package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDiamondOre extends Block {
    BlockType<BlockDiamondOre> TYPE = BlockTypeBuilder
            .builder(BlockDiamondOre.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_ORE, true)
            .addBasicComponents()
            .build();
}
