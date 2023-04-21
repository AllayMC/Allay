package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherWartBlock extends Block {
    BlockType<BlockNetherWartBlock> TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBlock.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
            .build();
}
