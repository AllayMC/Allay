package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockDeadBubbleCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeadBubbleCoralBehaviorInitializer {
  static void init() {
    BlockTypes.DEAD_BUBBLE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadBubbleCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL)
            .build();
  }
}
