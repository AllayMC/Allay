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
public interface BlockRedMushroomBlock extends Block {
    BlockType<BlockRedMushroomBlock> TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBlock.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .addBasicComponents()
            .build();
}
