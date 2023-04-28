package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneOre extends Block {
    BlockType<BlockRedstoneOre> TYPE = BlockTypeBuilder
            .builder(BlockRedstoneOre.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_ORE, true)
            .addBasicComponents()
            .build();
}
