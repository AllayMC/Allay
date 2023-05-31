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
public interface BlockStoneBlockSlab4 extends Block {
    BlockType<BlockStoneBlockSlab4> TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab4.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
