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
public interface BlockFlowingLava extends Block {
    BlockType<BlockFlowingLava> TYPE = BlockTypeBuilder
            .builder(BlockFlowingLava.class)
            .vanillaBlock(VanillaBlockId.FLOWING_LAVA, true)
            .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .addBasicComponents()
            .build();
}
