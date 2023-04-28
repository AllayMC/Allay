package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedNetherBrick extends Block {
    BlockType<BlockRedNetherBrick> TYPE = BlockTypeBuilder
            .builder(BlockRedNetherBrick.class)
            .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK, true)
            .addBasicComponents()
            .build();
}
