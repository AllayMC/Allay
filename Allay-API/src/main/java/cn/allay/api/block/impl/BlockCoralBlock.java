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
public interface BlockCoralBlock extends Block {
    BlockType<BlockCoralBlock> TYPE = BlockTypeBuilder
            .builder(BlockCoralBlock.class)
            .vanillaBlock(VanillaBlockId.CORAL_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
