package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRepeatingCommandBlock extends Block {
    BlockType<BlockRepeatingCommandBlock> TYPE = BlockTypeBuilder
            .builder(BlockRepeatingCommandBlock.class)
            .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK)
            .property(VanillaBlockPropertyTypes.CONDITIONAL_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
