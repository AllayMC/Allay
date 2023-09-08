package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBubbleColumnBehavior extends BlockBehavior {
    BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN_TYPE = BlockTypeBuilder
            .builder(BlockBubbleColumnBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN)
            .setProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
            .build();
}
