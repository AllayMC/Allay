package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardStainedGlassPane extends Block {
    BlockType<BlockHardStainedGlassPane> TYPE = BlockTypeBuilder
            .builder(BlockHardStainedGlassPane.class)
            .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
