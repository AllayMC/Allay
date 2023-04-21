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
public interface BlockStrippedBirchLog extends Block {
    BlockType<BlockStrippedBirchLog> TYPE = BlockTypeBuilder
            .builder(BlockStrippedBirchLog.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
