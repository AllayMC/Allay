package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateGoldOre extends Block {
    BlockType<BlockDeepslateGoldOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateGoldOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
            .build();
}
