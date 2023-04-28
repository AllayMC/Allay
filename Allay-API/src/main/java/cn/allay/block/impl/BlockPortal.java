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
public interface BlockPortal extends Block {
    BlockType<BlockPortal> TYPE = BlockTypeBuilder
            .builder(BlockPortal.class)
            .vanillaBlock(VanillaBlockId.PORTAL, true)
            .withProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
            .addBasicComponents()
            .build();
}
