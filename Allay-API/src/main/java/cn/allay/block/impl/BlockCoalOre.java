package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoalOre extends Block {
    BlockType<BlockCoalOre> TYPE = BlockTypeBuilder
            .builder(BlockCoalOre.class)
            .vanillaBlock(VanillaBlockId.COAL_ORE, true)
            .addBasicComponents()
            .build();
}
