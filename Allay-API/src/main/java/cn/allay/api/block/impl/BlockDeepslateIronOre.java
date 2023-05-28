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
public interface BlockDeepslateIronOre extends Block {
    BlockType<BlockDeepslateIronOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateIronOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
