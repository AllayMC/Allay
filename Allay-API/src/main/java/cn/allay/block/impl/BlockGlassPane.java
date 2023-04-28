package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlassPane extends Block {
    BlockType<BlockGlassPane> TYPE = BlockTypeBuilder
            .builder(BlockGlassPane.class)
            .vanillaBlock(VanillaBlockId.GLASS_PANE, true)
            .addBasicComponents()
            .build();
}
