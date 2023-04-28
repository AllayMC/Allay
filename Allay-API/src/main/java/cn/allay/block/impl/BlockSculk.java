package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculk extends Block {
    BlockType<BlockSculk> TYPE = BlockTypeBuilder
            .builder(BlockSculk.class)
            .vanillaBlock(VanillaBlockId.SCULK, true)
            .addBasicComponents()
            .build();
}
