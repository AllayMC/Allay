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
public interface BlockDeepslateEmeraldOre extends Block {
    BlockType<BlockDeepslateEmeraldOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateEmeraldOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
