package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockDeadBrainCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeadBrainCoralBehaviorInitializer {
  static void init() {
    BlockTypes.DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadBrainCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL)
            .build();
  }
}
