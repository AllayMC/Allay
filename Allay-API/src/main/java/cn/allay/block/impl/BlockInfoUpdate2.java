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
public interface BlockInfoUpdate2 extends Block {
    BlockType<BlockInfoUpdate2> TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdate2.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE2, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
