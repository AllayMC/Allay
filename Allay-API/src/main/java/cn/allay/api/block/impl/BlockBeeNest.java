package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBeeNest extends Block {
    BlockType<BlockBeeNest> TYPE = BlockTypeBuilder
            .builder(BlockBeeNest.class)
            .vanillaBlock(VanillaBlockId.BEE_NEST, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.HONEY_LEVEL)
            .addBasicComponents()
            .build();
}
