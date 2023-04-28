package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEmeraldOre extends Block {
    BlockType<BlockEmeraldOre> TYPE = BlockTypeBuilder
            .builder(BlockEmeraldOre.class)
            .vanillaBlock(VanillaBlockId.EMERALD_ORE, true)
            .addBasicComponents()
            .build();
}
