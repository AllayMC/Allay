package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeWool extends Block {
    BlockType<BlockOrangeWool> TYPE = BlockTypeBuilder
            .builder(BlockOrangeWool.class)
            .vanillaBlock(VanillaBlockId.ORANGE_WOOL)
            .build();
}
