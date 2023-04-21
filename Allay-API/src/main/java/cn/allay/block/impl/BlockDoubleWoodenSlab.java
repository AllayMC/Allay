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
public interface BlockDoubleWoodenSlab extends Block {
    BlockType<BlockDoubleWoodenSlab> TYPE = BlockTypeBuilder
            .builder(BlockDoubleWoodenSlab.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB)
            .property(VanillaBlockPropertyTypes.TOP_SLOT_BIT,
                    VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
}
