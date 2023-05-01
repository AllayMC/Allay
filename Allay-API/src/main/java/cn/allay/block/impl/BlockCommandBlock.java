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
public interface BlockCommandBlock extends Block {
    BlockType<BlockCommandBlock> TYPE = BlockTypeBuilder
            .builder(BlockCommandBlock.class)
            .vanillaBlock(VanillaBlockId.COMMAND_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
