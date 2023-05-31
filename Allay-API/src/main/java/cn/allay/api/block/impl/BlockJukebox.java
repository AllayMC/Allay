package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJukebox extends Block {
    BlockType<BlockJukebox> TYPE = BlockTypeBuilder
            .builder(BlockJukebox.class)
            .vanillaBlock(VanillaBlockId.JUKEBOX, true)
            .addBasicComponents()
            .build();
}
