package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFungus extends Block {
    BlockType<BlockCrimsonFungus> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFungus.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS, true)
            .addBasicComponents()
            .build();
}
