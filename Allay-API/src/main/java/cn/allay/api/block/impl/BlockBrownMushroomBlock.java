package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownMushroomBlock extends Block {
    BlockType<BlockBrownMushroomBlock> TYPE = BlockTypeBuilder
            .builder(BlockBrownMushroomBlock.class)
            .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
