package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockCyanConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockCyanConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CONCRETE_POWDER)
            .build();
  }
}
