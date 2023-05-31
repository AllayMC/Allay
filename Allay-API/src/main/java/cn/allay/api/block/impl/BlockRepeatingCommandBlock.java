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
public interface BlockRepeatingCommandBlock extends Block {
    BlockType<BlockRepeatingCommandBlock> TYPE = BlockTypeBuilder
            .builder(BlockRepeatingCommandBlock.class)
            .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
