package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTiles extends Block {
    BlockType<BlockDeepslateTiles> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTiles.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES, true)
            .addBasicComponents()
            .build();
}
