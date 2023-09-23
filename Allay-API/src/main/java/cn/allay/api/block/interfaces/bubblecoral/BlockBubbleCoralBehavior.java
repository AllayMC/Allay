package cn.allay.api.block.interfaces.bubblecoral;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBubbleCoralBehavior extends BlockBehavior {
    BlockType<BlockBubbleCoralBehavior> BUBBLE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockBubbleCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_CORAL)
            .build();
}
