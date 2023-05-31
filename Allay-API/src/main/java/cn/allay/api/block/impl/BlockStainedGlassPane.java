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
public interface BlockStainedGlassPane extends Block {
    BlockType<BlockStainedGlassPane> TYPE = BlockTypeBuilder
            .builder(BlockStainedGlassPane.class)
            .vanillaBlock(VanillaBlockId.STAINED_GLASS_PANE, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
