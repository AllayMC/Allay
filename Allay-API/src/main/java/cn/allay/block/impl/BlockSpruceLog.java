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
public interface BlockSpruceLog extends Block {
    BlockType<BlockSpruceLog> TYPE = BlockTypeBuilder
            .builder(BlockSpruceLog.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_LOG)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
