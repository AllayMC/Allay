package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitDeepslateRedstoneOre extends Block {
    BlockType<BlockLitDeepslateRedstoneOre> TYPE = BlockTypeBuilder
            .builder(BlockLitDeepslateRedstoneOre.class)
            .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
