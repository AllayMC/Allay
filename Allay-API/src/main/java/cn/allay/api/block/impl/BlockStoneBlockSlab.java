package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab extends Block {
    BlockType<BlockStoneBlockSlab> TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE,
                    VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
