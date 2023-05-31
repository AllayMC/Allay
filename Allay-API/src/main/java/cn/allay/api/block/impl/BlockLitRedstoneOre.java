package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneOre extends Block {
    BlockType<BlockLitRedstoneOre> TYPE = BlockTypeBuilder
            .builder(BlockLitRedstoneOre.class)
            .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE, true)
            .addBasicComponents()
            .build();
}
