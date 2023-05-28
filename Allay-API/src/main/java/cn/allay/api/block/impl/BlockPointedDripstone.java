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
public interface BlockPointedDripstone extends Block {
    BlockType<BlockPointedDripstone> TYPE = BlockTypeBuilder
            .builder(BlockPointedDripstone.class)
            .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE, true)
            .withProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS,
                    VanillaBlockPropertyTypes.HANGING)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
