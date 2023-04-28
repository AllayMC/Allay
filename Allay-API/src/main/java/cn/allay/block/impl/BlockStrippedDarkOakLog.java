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
public interface BlockStrippedDarkOakLog extends Block {
    BlockType<BlockStrippedDarkOakLog> TYPE = BlockTypeBuilder
            .builder(BlockStrippedDarkOakLog.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
