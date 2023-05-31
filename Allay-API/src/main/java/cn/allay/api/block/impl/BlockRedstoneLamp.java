package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneLamp extends Block {
    BlockType<BlockRedstoneLamp> TYPE = BlockTypeBuilder
            .builder(BlockRedstoneLamp.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_LAMP, true)
            .addBasicComponents()
            .build();
}
