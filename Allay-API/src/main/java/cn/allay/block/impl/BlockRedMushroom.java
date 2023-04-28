package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedMushroom extends Block {
    BlockType<BlockRedMushroom> TYPE = BlockTypeBuilder
            .builder(BlockRedMushroom.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM, true)
            .addBasicComponents()
            .build();
}
