package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherBrick extends Block {
    BlockType<BlockNetherBrick> TYPE = BlockTypeBuilder
            .builder(BlockNetherBrick.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK)
            .build();
}
