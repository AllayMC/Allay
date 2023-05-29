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
public interface BlockSmallDripleafBlock extends Block {
    BlockType<BlockSmallDripleafBlock> TYPE = BlockTypeBuilder
            .builder(BlockSmallDripleafBlock.class)
            .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
