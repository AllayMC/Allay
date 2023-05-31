package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothStone extends Block {
    BlockType<BlockSmoothStone> TYPE = BlockTypeBuilder
            .builder(BlockSmoothStone.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_STONE, true)
            .addBasicComponents()
            .build();
}
