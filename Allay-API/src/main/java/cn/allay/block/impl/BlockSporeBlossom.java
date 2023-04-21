package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSporeBlossom extends Block {
    BlockType<BlockSporeBlossom> TYPE = BlockTypeBuilder
            .builder(BlockSporeBlossom.class)
            .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
            .build();
}
