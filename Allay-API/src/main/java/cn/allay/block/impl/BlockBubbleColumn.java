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
public interface BlockBubbleColumn extends Block {
    BlockType<BlockBubbleColumn> TYPE = BlockTypeBuilder
            .builder(BlockBubbleColumn.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN, true)
            .withProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
