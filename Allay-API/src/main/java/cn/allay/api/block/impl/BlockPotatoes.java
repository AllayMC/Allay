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
public interface BlockPotatoes extends Block {
    BlockType<BlockPotatoes> TYPE = BlockTypeBuilder
            .builder(BlockPotatoes.class)
            .vanillaBlock(VanillaBlockId.POTATOES, true)
            .withProperties(VanillaBlockPropertyTypes.GROWTH)
            .addBasicComponents()
            .build();
}
