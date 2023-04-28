package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

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
