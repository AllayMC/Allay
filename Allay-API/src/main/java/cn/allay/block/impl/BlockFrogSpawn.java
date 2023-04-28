package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFrogSpawn extends Block {
    BlockType<BlockFrogSpawn> TYPE = BlockTypeBuilder
            .builder(BlockFrogSpawn.class)
            .vanillaBlock(VanillaBlockId.FROG_SPAWN, true)
            .addBasicComponents()
            .build();
}
