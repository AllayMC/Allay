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
public interface BlockGlowFrame extends Block {
    BlockType<BlockGlowFrame> TYPE = BlockTypeBuilder
            .builder(BlockGlowFrame.class)
            .vanillaBlock(VanillaBlockId.GLOW_FRAME, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT,
                    VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .addBasicComponents()
            .build();
}
