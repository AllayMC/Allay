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
public interface BlockCherryLog extends Block {
    BlockType<BlockCherryLog> TYPE = BlockTypeBuilder
            .builder(BlockCherryLog.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LOG)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
