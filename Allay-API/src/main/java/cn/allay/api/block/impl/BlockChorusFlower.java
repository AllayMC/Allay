package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChorusFlower extends Block {
    BlockType<BlockChorusFlower> TYPE = BlockTypeBuilder
            .builder(BlockChorusFlower.class)
            .vanillaBlock(VanillaBlockId.CHORUS_FLOWER, true)
            .withProperties(VanillaBlockPropertyTypes.CHORUS_FLOWER_AGE)
            .addBasicComponents()
            .build();
}
