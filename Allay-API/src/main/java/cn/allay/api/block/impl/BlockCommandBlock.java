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
public interface BlockCommandBlock extends Block {
    BlockType<BlockCommandBlock> TYPE = BlockTypeBuilder
            .builder(BlockCommandBlock.class)
            .vanillaBlock(VanillaBlockId.COMMAND_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
