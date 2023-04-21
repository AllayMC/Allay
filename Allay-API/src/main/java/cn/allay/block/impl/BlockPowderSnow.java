package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPowderSnow extends Block {
    BlockType<BlockPowderSnow> TYPE = BlockTypeBuilder
            .builder(BlockPowderSnow.class)
            .vanillaBlock(VanillaBlockId.POWDER_SNOW)
            .build();
}
