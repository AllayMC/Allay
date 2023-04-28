package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateEmeraldOre extends Block {
    BlockType<BlockDeepslateEmeraldOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateEmeraldOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE, true)
            .addBasicComponents()
            .build();
}
