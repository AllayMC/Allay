package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEmeraldBlock extends Block {
    BlockType<BlockEmeraldBlock> TYPE = BlockTypeBuilder
            .builder(BlockEmeraldBlock.class)
            .vanillaBlock(VanillaBlockId.EMERALD_BLOCK, true)
            .addBasicComponents()
            .build();
}
