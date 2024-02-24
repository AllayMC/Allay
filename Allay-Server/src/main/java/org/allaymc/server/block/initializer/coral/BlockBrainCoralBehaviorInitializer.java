package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockBrainCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrainCoralBehaviorInitializer {
  static void init() {
    BlockTypes.BRAIN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockBrainCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.BRAIN_CORAL)
            .build();
  }
}
