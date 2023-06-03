package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAncientDebris extends Block {
    BlockType<BlockAncientDebris> TYPE = BlockTypeBuilder
            .builder(BlockAncientDebris.class)
            .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS, true)
            .addBasicComponents()
            .build();
}
