package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCryingObsidian extends Block {
    BlockType<BlockCryingObsidian> TYPE = BlockTypeBuilder
            .builder(BlockCryingObsidian.class)
            .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
