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
public interface BlockDeepslateCoalOre extends Block {
    BlockType<BlockDeepslateCoalOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCoalOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
