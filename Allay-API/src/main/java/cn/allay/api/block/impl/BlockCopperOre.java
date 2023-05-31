package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperOre extends Block {
    BlockType<BlockCopperOre> TYPE = BlockTypeBuilder
            .builder(BlockCopperOre.class)
            .vanillaBlock(VanillaBlockId.COPPER_ORE, true)
            .addBasicComponents()
            .build();
}
