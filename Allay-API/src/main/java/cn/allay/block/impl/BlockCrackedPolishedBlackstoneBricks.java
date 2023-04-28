package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedPolishedBlackstoneBricks extends Block {
    BlockType<BlockCrackedPolishedBlackstoneBricks> TYPE = BlockTypeBuilder
            .builder(BlockCrackedPolishedBlackstoneBricks.class)
            .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
            .addBasicComponents()
            .build();
}
