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
public interface BlockGildedBlackstone extends Block {
    BlockType<BlockGildedBlackstone> TYPE = BlockTypeBuilder
            .builder(BlockGildedBlackstone.class)
            .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
