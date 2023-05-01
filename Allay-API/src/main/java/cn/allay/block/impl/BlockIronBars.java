package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIronBars extends Block {
    BlockType<BlockIronBars> TYPE = BlockTypeBuilder
            .builder(BlockIronBars.class)
            .vanillaBlock(VanillaBlockId.IRON_BARS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
