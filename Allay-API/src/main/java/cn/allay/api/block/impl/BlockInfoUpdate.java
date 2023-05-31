package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdate extends Block {
    BlockType<BlockInfoUpdate> TYPE = BlockTypeBuilder
            .builder(BlockInfoUpdate.class)
            .vanillaBlock(VanillaBlockId.INFO_UPDATE, true)
            .addBasicComponents()
            .build();
}
