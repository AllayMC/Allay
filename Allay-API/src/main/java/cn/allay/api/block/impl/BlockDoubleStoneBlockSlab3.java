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
public interface BlockDoubleStoneBlockSlab3 extends Block {
    BlockType<BlockDoubleStoneBlockSlab3> TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab3.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
