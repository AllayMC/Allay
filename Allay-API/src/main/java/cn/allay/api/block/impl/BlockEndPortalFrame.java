package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndPortalFrame extends Block {
    BlockType<BlockEndPortalFrame> TYPE = BlockTypeBuilder
            .builder(BlockEndPortalFrame.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
