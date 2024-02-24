package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockDeadFireCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeadFireCoralBehaviorInitializer {
  static void init() {
    BlockTypes.DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockDeadFireCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL)
            .build();
  }
}
