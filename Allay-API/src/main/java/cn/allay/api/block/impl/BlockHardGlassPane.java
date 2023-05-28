package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

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
