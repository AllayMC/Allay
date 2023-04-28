package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitDeepslateRedstoneOre extends Block {
    BlockType<BlockLitDeepslateRedstoneOre> TYPE = BlockTypeBuilder
            .builder(BlockLitDeepslateRedstoneOre.class)
            .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE, true)
            .addBasicComponents()
            .build();
}
