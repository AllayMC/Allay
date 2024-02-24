package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEndRodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndRodBehaviorInitializer {
  static void init() {
    BlockTypes.END_ROD_TYPE = BlockTypeBuilder
            .builder(BlockEndRodBehavior.class)
            .vanillaBlock(VanillaBlockId.END_ROD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
