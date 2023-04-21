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
public interface BlockPotatoes extends Block {
    BlockType<BlockPotatoes> TYPE = BlockTypeBuilder
            .builder(BlockPotatoes.class)
            .vanillaBlock(VanillaBlockId.POTATOES)
            .property(VanillaBlockPropertyTypes.GROWTH)
            .build();
}
