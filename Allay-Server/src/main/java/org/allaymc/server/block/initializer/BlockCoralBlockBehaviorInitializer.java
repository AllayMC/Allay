package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCoralBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoralBlockBehaviorInitializer {
  static void init() {
    BlockTypes.CORAL_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCoralBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CORAL_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
  }
}
