package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWitherRose extends Block {
    BlockType<BlockWitherRose> TYPE = BlockTypeBuilder
            .builder(BlockWitherRose.class)
            .vanillaBlock(VanillaBlockId.WITHER_ROSE, true)
            .addBasicComponents()
            .build();
}
