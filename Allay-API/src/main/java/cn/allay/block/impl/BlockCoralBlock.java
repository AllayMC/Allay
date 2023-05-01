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
public interface BlockCoralBlock extends Block {
    BlockType<BlockCoralBlock> TYPE = BlockTypeBuilder
            .builder(BlockCoralBlock.class)
            .vanillaBlock(VanillaBlockId.CORAL_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
