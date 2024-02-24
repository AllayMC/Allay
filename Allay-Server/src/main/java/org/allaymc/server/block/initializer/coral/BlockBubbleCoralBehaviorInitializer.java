package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockBubbleCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBubbleCoralBehaviorInitializer {
  static void init() {
    BlockTypes.BUBBLE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockBubbleCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_CORAL)
            .build();
  }
}
