package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
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
