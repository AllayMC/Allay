package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLava extends Block {
    BlockType<BlockLava> TYPE = BlockTypeBuilder
            .builder(BlockLava.class)
            .vanillaBlock(VanillaBlockId.LAVA, true)
            .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .addBasicComponents()
            .build();
}
