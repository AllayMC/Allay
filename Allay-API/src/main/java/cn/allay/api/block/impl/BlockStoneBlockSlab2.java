package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab2 extends Block {
    BlockType<BlockStoneBlockSlab2> TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab2.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
