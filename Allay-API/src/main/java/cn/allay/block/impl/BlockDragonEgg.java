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
public interface BlockDragonEgg extends Block {
    BlockType<BlockDragonEgg> TYPE = BlockTypeBuilder
            .builder(BlockDragonEgg.class)
            .vanillaBlock(VanillaBlockId.DRAGON_EGG, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
