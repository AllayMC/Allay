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
public interface BlockMangroveRoots extends Block {
    BlockType<BlockMangroveRoots> TYPE = BlockTypeBuilder
            .builder(BlockMangroveRoots.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
