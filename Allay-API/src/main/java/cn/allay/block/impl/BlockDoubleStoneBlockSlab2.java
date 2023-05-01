package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleStoneBlockSlab2 extends Block {
    BlockType<BlockDoubleStoneBlockSlab2> TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab2.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
