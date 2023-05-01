package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateTiles extends Block {
    BlockType<BlockCrackedDeepslateTiles> TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateTiles.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
