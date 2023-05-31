package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlowingobsidian extends Block {
    BlockType<BlockGlowingobsidian> TYPE = BlockTypeBuilder
            .builder(BlockGlowingobsidian.class)
            .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN, true)
            .addBasicComponents()
            .build();
}
