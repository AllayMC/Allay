package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGlassPane extends Block {
    BlockType<BlockHardGlassPane> TYPE = BlockTypeBuilder
            .builder(BlockHardGlassPane.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
