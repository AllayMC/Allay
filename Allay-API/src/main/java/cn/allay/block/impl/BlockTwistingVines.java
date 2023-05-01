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
public interface BlockTwistingVines extends Block {
    BlockType<BlockTwistingVines> TYPE = BlockTypeBuilder
            .builder(BlockTwistingVines.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
