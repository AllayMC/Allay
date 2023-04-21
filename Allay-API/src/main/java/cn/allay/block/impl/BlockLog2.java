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
public interface BlockLog2 extends Block {
    BlockType<BlockLog2> TYPE = BlockTypeBuilder
            .builder(BlockLog2.class)
            .vanillaBlock(VanillaBlockId.LOG2)
            .property(VanillaBlockPropertyTypes.NEW_LOG_TYPE,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
