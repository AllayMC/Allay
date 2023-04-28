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
public interface BlockDoubleStoneBlockSlab extends Block {
    BlockType<BlockDoubleStoneBlockSlab> TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
