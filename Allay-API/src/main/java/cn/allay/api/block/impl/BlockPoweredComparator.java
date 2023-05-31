package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPoweredComparator extends Block {
    BlockType<BlockPoweredComparator> TYPE = BlockTypeBuilder
            .builder(BlockPoweredComparator.class)
            .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.OUTPUT_LIT_BIT,
                    VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
            .addBasicComponents()
            .build();
}
