package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockShroomlight extends Block {
    BlockType<BlockShroomlight> TYPE = BlockTypeBuilder
            .builder(BlockShroomlight.class)
            .vanillaBlock(VanillaBlockId.SHROOMLIGHT, true)
            .addBasicComponents()
            .build();
}
