package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdate2 extends Block {
    BlockType<BlockInfoUpdate2> TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdate2.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE2, true)
            .addBasicComponents()
            .build();
}
