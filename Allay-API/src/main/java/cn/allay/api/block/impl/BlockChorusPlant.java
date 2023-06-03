package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChorusPlant extends Block {
    BlockType<BlockChorusPlant> TYPE = BlockTypeBuilder
            .builder(BlockChorusPlant.class)
            .vanillaBlock(VanillaBlockId.CHORUS_PLANT, true)
            .addBasicComponents()
            .build();
}
