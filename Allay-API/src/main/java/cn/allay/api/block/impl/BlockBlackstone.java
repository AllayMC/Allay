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
public interface BlockBlackstone extends Block {
    BlockType<BlockBlackstone> TYPE = BlockTypeBuilder
            .builder(BlockBlackstone.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
