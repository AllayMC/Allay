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
public interface BlockSmallDripleafBlock extends Block {
    BlockType<BlockSmallDripleafBlock> TYPE = BlockTypeBuilder
            .builder(BlockSmallDripleafBlock.class)
            .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .addBasicComponents()
            .build();
}
