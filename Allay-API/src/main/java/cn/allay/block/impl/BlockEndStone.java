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
public interface BlockEndStone extends Block {
    BlockType<BlockEndStone> TYPE = BlockTypeBuilder
            .builder(BlockEndStone.class)
            .vanillaBlock(VanillaBlockId.END_STONE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
