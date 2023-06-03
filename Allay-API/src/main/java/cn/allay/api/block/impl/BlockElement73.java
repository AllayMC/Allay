package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement73 extends Block {
    BlockType<BlockElement73> TYPE = BlockTypeBuilder
            .builder(BlockElement73.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_73, true)
            .addBasicComponents()
            .build();
}
