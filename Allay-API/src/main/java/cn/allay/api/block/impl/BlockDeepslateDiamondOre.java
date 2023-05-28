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
public interface BlockDeepslateDiamondOre extends Block {
    BlockType<BlockDeepslateDiamondOre> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateDiamondOre.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
