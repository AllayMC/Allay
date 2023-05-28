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
public interface BlockWoodenSlab extends Block {
    BlockType<BlockWoodenSlab> TYPE = BlockTypeBuilder
            .builder(BlockWoodenSlab.class)
            .vanillaBlock(VanillaBlockId.WOODEN_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT,
                    VanillaBlockPropertyTypes.WOOD_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
