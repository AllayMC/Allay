package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueWool extends Block {
    BlockType<BlockLightBlueWool> TYPE = BlockTypeBuilder
            .builder(BlockLightBlueWool.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL)
            .build();
}
