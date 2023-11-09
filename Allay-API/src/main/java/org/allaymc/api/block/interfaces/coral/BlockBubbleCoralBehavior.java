package org.allaymc.api.block.interfaces.coral;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

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
