package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockTubeCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTubeCoralBehaviorInitializer {
  static void init() {
    BlockTypes.TUBE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockTubeCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.TUBE_CORAL)
            .build();
  }
}
