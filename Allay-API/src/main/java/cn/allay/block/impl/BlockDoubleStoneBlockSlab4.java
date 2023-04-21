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
public interface BlockDoubleStoneBlockSlab4 extends Block {
    BlockType<BlockDoubleStoneBlockSlab4> TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab4.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4)
            .property(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
