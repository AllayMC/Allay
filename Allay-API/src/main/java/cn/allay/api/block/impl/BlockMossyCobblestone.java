package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMossyCobblestone extends Block {
    BlockType<BlockMossyCobblestone> TYPE = BlockTypeBuilder
            .builder(BlockMossyCobblestone.class)
            .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE, true)
            .addBasicComponents()
            .build();
}
