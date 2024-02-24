package org.allaymc.server.block.initializer.coral;

import org.allaymc.api.block.interfaces.coral.BlockFireCoralBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFireCoralBehaviorInitializer {
  static void init() {
    BlockTypes.FIRE_CORAL_TYPE = BlockTypeBuilder
            .builder(BlockFireCoralBehavior.class)
            .vanillaBlock(VanillaBlockId.FIRE_CORAL)
            .build();
  }
}
