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
public interface BlockDarkOakLog extends Block {
    BlockType<BlockDarkOakLog> TYPE = BlockTypeBuilder
            .builder(BlockDarkOakLog.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_LOG)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
