package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBricks extends Block {
    BlockType<BlockDeepslateBricks> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBricks.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS, true)
            .addBasicComponents()
            .build();
}
