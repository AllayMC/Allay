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
public interface BlockPinkPetals extends Block {
    BlockType<BlockPinkPetals> TYPE = BlockTypeBuilder
            .builder(BlockPinkPetals.class)
            .vanillaBlock(VanillaBlockId.PINK_PETALS, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.GROWTH)
            .addBasicComponents()
            .build();
}
