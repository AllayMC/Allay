package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockHornCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHornCoralBehaviorInitializer {
  static void init() {
    BlockTypes.HORN_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockHornCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.HORN_CORAL)
            .build();
  }
}
