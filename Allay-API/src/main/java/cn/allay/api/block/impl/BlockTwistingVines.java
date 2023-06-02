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
public interface BlockTwistingVines extends Block {
    BlockType<BlockTwistingVines> TYPE = BlockTypeBuilder
            .builder(BlockTwistingVines.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .addBasicComponents()
            .build();
}
