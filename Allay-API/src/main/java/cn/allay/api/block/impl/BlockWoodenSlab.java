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
public interface BlockWoodenSlab extends Block {
    BlockType<BlockWoodenSlab> TYPE = BlockTypeBuilder
            .builder(BlockWoodenSlab.class)
            .vanillaBlock(VanillaBlockId.WOODEN_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT,
                    VanillaBlockPropertyTypes.WOOD_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
