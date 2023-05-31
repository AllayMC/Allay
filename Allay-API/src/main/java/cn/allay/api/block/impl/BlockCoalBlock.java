package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoalBlock extends Block {
    BlockType<BlockCoalBlock> TYPE = BlockTypeBuilder
            .builder(BlockCoalBlock.class)
            .vanillaBlock(VanillaBlockId.COAL_BLOCK, true)
            .addBasicComponents()
            .build();
}
