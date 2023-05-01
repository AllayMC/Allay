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
public interface BlockStoneBlockSlab4 extends Block {
    BlockType<BlockStoneBlockSlab4> TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab4.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
