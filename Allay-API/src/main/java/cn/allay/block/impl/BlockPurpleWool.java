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
public interface BlockPurpleWool extends Block {
    BlockType<BlockPurpleWool> TYPE = BlockTypeBuilder
            .builder(BlockPurpleWool.class)
            .vanillaBlock(VanillaBlockId.PURPLE_WOOL, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
