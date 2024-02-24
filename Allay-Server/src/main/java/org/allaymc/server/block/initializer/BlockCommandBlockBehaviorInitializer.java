package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCommandBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCommandBlockBehaviorInitializer {
  static void init() {
    BlockTypes.COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
