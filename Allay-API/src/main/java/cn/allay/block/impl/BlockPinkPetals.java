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
public interface BlockPinkPetals extends Block {
    BlockType<BlockPinkPetals> TYPE = BlockTypeBuilder
            .builder(BlockPinkPetals.class)
            .vanillaBlock(VanillaBlockId.PINK_PETALS)
            .property(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.GROWTH)
            .build();
}
