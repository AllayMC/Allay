package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTwistingVines extends Block {
    BlockType<BlockTwistingVines> TYPE = BlockTypeBuilder
            .builder(BlockTwistingVines.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
