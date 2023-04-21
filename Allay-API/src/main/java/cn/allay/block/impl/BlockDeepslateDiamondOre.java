package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateDiamondOre extends Block {
    BlockType<BlockDeepslateDiamondOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateDiamondOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE)
            .build();
}
