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
public interface BlockCactus extends Block {
    BlockType<BlockCactus> TYPE = BlockTypeBuilder
            .builder(BlockCactus.class)
            .vanillaBlock(VanillaBlockId.CACTUS, true)
            .withProperties(VanillaBlockPropertyTypes.AGE)
            .addBasicComponents()
            .build();
}
