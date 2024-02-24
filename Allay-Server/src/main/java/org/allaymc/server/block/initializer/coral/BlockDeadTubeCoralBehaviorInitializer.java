package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockDeadTubeCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeadTubeCoralBehaviorInitializer {
  static void init() {
    BlockTypes.DEAD_TUBE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadTubeCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_TUBE_CORAL)
            .build();
  }
}
