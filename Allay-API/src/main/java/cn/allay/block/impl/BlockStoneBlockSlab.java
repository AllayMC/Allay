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
public interface BlockStoneBlockSlab extends Block {
    BlockType<BlockStoneBlockSlab> TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
            .property(VanillaBlockPropertyTypes.STONE_SLAB_TYPE,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
