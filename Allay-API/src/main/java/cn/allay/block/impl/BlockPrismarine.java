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
public interface BlockPrismarine extends Block {
    BlockType<BlockPrismarine> TYPE = BlockTypeBuilder
            .builder(BlockPrismarine.class)
            .vanillaBlock(VanillaBlockId.PRISMARINE)
            .property(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
            .build();
}
