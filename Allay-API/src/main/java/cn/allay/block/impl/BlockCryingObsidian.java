package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCryingObsidian extends Block {
    BlockType<BlockCryingObsidian> TYPE = BlockTypeBuilder
            .builder(BlockCryingObsidian.class)
            .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN, true)
            .addBasicComponents()
            .build();
}
