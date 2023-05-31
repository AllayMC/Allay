package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherrack extends Block {
    BlockType<BlockNetherrack> TYPE = BlockTypeBuilder
            .builder(BlockNetherrack.class)
            .vanillaBlock(VanillaBlockId.NETHERRACK, true)
            .addBasicComponents()
            .build();
}
