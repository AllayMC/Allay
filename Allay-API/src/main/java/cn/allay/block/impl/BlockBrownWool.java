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
public interface BlockBrownWool extends Block {
    BlockType<BlockBrownWool> TYPE = BlockTypeBuilder
            .builder(BlockBrownWool.class)
            .vanillaBlock(VanillaBlockId.BROWN_WOOL, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
