package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDoubleStoneBlockSlab extends Block {
    BlockType<BlockDoubleStoneBlockSlab> TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
