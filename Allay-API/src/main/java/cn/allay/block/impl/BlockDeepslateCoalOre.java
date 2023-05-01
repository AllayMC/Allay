package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

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
