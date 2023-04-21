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
public interface BlockLog extends Block {
    BlockType<BlockLog> TYPE = BlockTypeBuilder
            .builder(BlockLog.class)
            .vanillaBlock(VanillaBlockId.LOG)
            .property(VanillaBlockPropertyTypes.OLD_LOG_TYPE,
                    VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
