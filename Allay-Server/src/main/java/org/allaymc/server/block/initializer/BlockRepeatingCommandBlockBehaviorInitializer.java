package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRepeatingCommandBlockBehaviorInitializer {
  static void init() {
    BlockTypes.REPEATING_COMMAND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRepeatingCommandBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
