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
public interface BlockChainCommandBlock extends Block {
    BlockType<BlockChainCommandBlock> TYPE = BlockTypeBuilder
            .builder(BlockChainCommandBlock.class)
            .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
