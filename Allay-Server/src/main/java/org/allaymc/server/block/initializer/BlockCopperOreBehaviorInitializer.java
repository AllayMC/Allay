package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCopperOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperOreBehaviorInitializer {
  static void init() {
    BlockTypes.COPPER_ORE_TYPE = BlockTypeBuilder
            .builder(BlockCopperOreBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_ORE)
            .build();
  }
}
